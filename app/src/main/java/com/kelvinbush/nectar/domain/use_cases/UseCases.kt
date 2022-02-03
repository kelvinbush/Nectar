package com.kelvinbush.nectar.domain.use_cases

import com.kelvinbush.nectar.domain.use_cases.add_to_cart.AddToCartUseCase
import com.kelvinbush.nectar.domain.use_cases.get_all_products.GetAllProductsUseCase
import com.kelvinbush.nectar.domain.use_cases.get_cart.GetCartUseCase
import com.kelvinbush.nectar.domain.use_cases.get_id_token.GetIdToken
import com.kelvinbush.nectar.domain.use_cases.login.LoginUseCase
import com.kelvinbush.nectar.domain.use_cases.remove_from_cart.RemoveCartUseCase
import com.kelvinbush.nectar.domain.use_cases.user_signup.SignupUseCase

data class UseCases(
    val getCartUseCase: GetCartUseCase,
    val getAllProductsUseCase: GetAllProductsUseCase,
    val loginUseCase: LoginUseCase,
    val addToCartUseCase: AddToCartUseCase,
    val getIdToken: GetIdToken,
    val removeCartUseCase: RemoveCartUseCase,
    val signupUseCase: SignupUseCase
)