package di

import domain.usecase.GetTransactionHistoryUseCase
import domain.usecase.IGetTransactionHistoryUseCase
import domain.usecase.IInProgressTrackerUseCase
import domain.usecase.IManageAuthenticationUseCase
import domain.usecase.IManageCartUseCase
import domain.usecase.IChatUseCase
import domain.usecase.IManageFavouriteUseCase
import domain.usecase.IGetNotificationsUseCase
import domain.usecase.IGetOffersUseCase
import domain.usecase.IManageSettingUseCase
import domain.usecase.IExploreRestaurantUseCase
import domain.usecase.ISearchUseCase
import domain.usecase.SearchUseCase
import domain.usecase.InProgressTrackerUseCase
import domain.usecase.ManageAuthenticationUseCase
import domain.usecase.ManageCartUseCase
import domain.usecase.ChatUseCase
import domain.usecase.ManageFavouriteUseCase
import domain.usecase.GetNotificationsUseCase
import domain.usecase.GetOffersUseCase
import domain.usecase.ExploreRestaurantUseCase
import domain.usecase.IManageProfileUseCase
import domain.usecase.ManageProfileUseCase
import domain.usecase.ManageSettingUseCase
import domain.usecase.validation.IValidationUseCase
import domain.usecase.validation.ValidationUseCaseUseCase
import org.koin.core.module.dsl.bind
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module

val useCaseModule = module {
    singleOf(::ManageAuthenticationUseCase) { bind<IManageAuthenticationUseCase>() }
    singleOf(::GetOffersUseCase) { bind<IGetOffersUseCase>() }
    singleOf(::ManageSettingUseCase) { bind<IManageSettingUseCase>() }
    singleOf(::InProgressTrackerUseCase) { bind<IInProgressTrackerUseCase>() }
    singleOf(::ValidationUseCaseUseCase) { bind<IValidationUseCase>() }
    singleOf(::GetNotificationsUseCase) { bind<IGetNotificationsUseCase>() }
    singleOf(::GetTransactionHistoryUseCase) { bind<IGetTransactionHistoryUseCase>() }
    singleOf(::ExploreRestaurantUseCase) { bind<IExploreRestaurantUseCase>() }
    singleOf(::ManageCartUseCase) { bind<IManageCartUseCase>() }
    singleOf(::ChatUseCase) { bind<IChatUseCase>() }
    singleOf(::ManageFavouriteUseCase) { bind<IManageFavouriteUseCase>() }
    singleOf(::SearchUseCase) { bind<ISearchUseCase>() }
    singleOf(::ManageProfileUseCase) { bind<IManageProfileUseCase>() }

}
