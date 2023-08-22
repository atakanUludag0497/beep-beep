package presentation.order

import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.model.rememberScreenModel
import cafe.adriel.voyager.navigator.Navigator
import presentation.base.BaseScreen
import presentation.composables.OrderCard
import presentation.login.LoginScreen

class OrderScreen :
    BaseScreen<OrderScreenModel, OrderScreenUiState, OrderScreenUiEffect, OrderScreenInteractionListener>() {

    @Composable
    override fun Content() {
        val screenModel = rememberScreenModel { OrderScreenModel() }
        initScreen(screenModel)
    }

    override fun onEffect(effect: OrderScreenUiEffect, navigator: Navigator) {
        when (effect) {
            is OrderScreenUiEffect.Back -> navigator.push(LoginScreen())
        }
    }

    @Composable
    override fun onRender(state: OrderScreenUiState, listener: OrderScreenInteractionListener) {
        OrderCard(items = state.orders[0].orderMeals, totalPrice = state.orders[0].totalPrice) {
        }
    }


}