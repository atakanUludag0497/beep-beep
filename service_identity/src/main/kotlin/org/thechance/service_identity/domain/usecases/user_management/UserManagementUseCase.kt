package org.thechance.service_identity.domain.usecases.user_management

import org.thechance.service_identity.domain.entity.Permission
import org.thechance.service_identity.domain.entity.User

interface UserManagementUseCase {

    suspend fun addPermissionToUser(userId: String, permissionId: Int): Boolean

    suspend fun removePermissionFromUser(userId: String, permissionId: Int): Boolean

    suspend fun getUserPermissions(userId: String): List<Permission>

    suspend fun getUserById(id: String): User

    suspend fun getUsersList(fullName: String, username: String): List<User>

}