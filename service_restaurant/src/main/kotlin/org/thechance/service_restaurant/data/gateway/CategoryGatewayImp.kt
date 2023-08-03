package org.thechance.service_restaurant.data.gateway

import com.mongodb.client.model.Updates
import org.bson.types.ObjectId
import org.koin.core.annotation.Single
import org.litote.kmongo.*
import org.litote.kmongo.coroutine.aggregate
import org.thechance.service_restaurant.data.DataBaseContainer
import org.thechance.service_restaurant.data.collection.CategoryCollection
import org.thechance.service_restaurant.data.collection.CategoryRestaurant
import org.thechance.service_restaurant.data.collection.RestaurantCollection
import org.thechance.service_restaurant.data.collection.mapper.toCollection
import org.thechance.service_restaurant.data.collection.mapper.toEntity
import org.thechance.service_restaurant.data.utils.isSuccessfullyUpdated
import org.thechance.service_restaurant.data.utils.paginate
import org.thechance.service_restaurant.data.utils.toObjectIds
import org.thechance.service_restaurant.entity.Category
import org.thechance.service_restaurant.entity.Restaurant

@Single
class CategoryGatewayImp(private val container: DataBaseContainer) : CategoryGateway {

    private val categoryCollection by lazy { container.database.getCollection<CategoryCollection>() }
    private val restaurantCollection by lazy { container.database.getCollection<RestaurantCollection>() }

    //region Category
    override suspend fun getCategories(page: Int, limit: Int): List<Category> {
        return categoryCollection.find(CategoryCollection::isDeleted eq false)
            .paginate(page, limit).toList().toEntity()
    }

    override suspend fun getCategory(categoryId: String): Category? {
        return categoryCollection.aggregate<CategoryCollection>(
            match(and(CategoryCollection::id eq ObjectId(categoryId), CategoryCollection::isDeleted eq false)),
            project(CategoryCollection::name, CategoryCollection::id)
        ).toList().firstOrNull()?.toEntity()
    }

    override suspend fun getRestaurantsInCategory(categoryId: String): List<Restaurant> {
        return categoryCollection.aggregate<CategoryRestaurant>(
            match(CategoryCollection::id eq ObjectId(categoryId)),
            lookup(
                from = "restaurantCollection",
                resultProperty = CategoryRestaurant::restaurants,
                pipeline = arrayOf(match(RestaurantCollection::isDeleted eq false))
            ),
        ).toList().first().restaurants.toEntity()
    }

    override suspend fun addCategory(category: Category): Boolean {
        return categoryCollection.insertOne(category.toCollection()).wasAcknowledged()
    }

    override suspend fun addRestaurantsToCategory(categoryId: String, restaurantIds: List<String>): Boolean {
        val resultAddToRestaurant = restaurantCollection.updateMany(
            RestaurantCollection::id `in` restaurantIds.toObjectIds(),
            addToSet(RestaurantCollection::categoryIds, ObjectId(categoryId))
        ).isSuccessfullyUpdated()

        val resultAddToCategory = categoryCollection.updateOneById(
            ObjectId(categoryId),
            update = Updates.addEachToSet(CategoryCollection::restaurantIds.name, restaurantIds.toObjectIds())
        ).isSuccessfullyUpdated()
        return resultAddToCategory and resultAddToRestaurant
    }

    override suspend fun updateCategory(category: Category): Boolean {
        return categoryCollection.updateOneById(
            id = ObjectId(category.id),
            update = category.toCollection(),
            updateOnlyNotNullProperties = true
        ).isSuccessfullyUpdated()
    }

    override suspend fun deleteCategory(categoryId: String): Boolean {
        return categoryCollection.updateOneById(
            id = ObjectId(categoryId),
            update = Updates.set(CategoryCollection::isDeleted.name, true),
        ).isSuccessfullyUpdated()
    }

    override suspend fun deleteRestaurantsInCategory(categoryId: String, restaurantIds: List<String>): Boolean {
        val resultDeleteFromRestaurant = restaurantCollection.updateMany(
            RestaurantCollection::id `in` restaurantIds.toObjectIds(),
            pull(RestaurantCollection::categoryIds, ObjectId(categoryId))
        ).isSuccessfullyUpdated()

        val resultDeleteFromCategory = categoryCollection.updateOneById(
            ObjectId(categoryId),
            pullAll(CategoryCollection::restaurantIds, restaurantIds.toObjectIds())
        ).isSuccessfullyUpdated()
        return resultDeleteFromRestaurant and resultDeleteFromCategory
    }
    //endregion
}