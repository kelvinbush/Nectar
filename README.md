# Nectar 
An android ecommerce application for groceries consuming the [Fruity API](https://github.com/kelvinbush/Fruity-S). Using
Jetpack compose, authentication with jwt and clean architecture.


### Features
* User can sign up and register on the app - authentication by jwt provided by the backend ([Fruity API](https://github.com/kelvinbush/Fruity-S)) or via social login.
* User can explore available products on the shop.
* User can view details of the product.
* User can add a product to cart.
* User can manage products in the cart (Increase or decrease or remove from cart).
* User can manage their account.

### The final app screens:

<p align="center">
<img src="images/1.png" width="33%"/>
<img src="images/2.png" width="33%"/> 
<img src="images/3.png" width="33%"/> 
<img src="images/4.png" width="33%"/>
<img src="images/5.png" width="33%"/> 
<img src="images/6.png" width="33%"/> 
</p>

* Tech-stack
    * [Kotlin](https://kotlinlang.org/) - a cross-platform, statically typed, general-purpose programming language with type inference.
    * [Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html) - perform background operations.
    * [Flow](https://kotlinlang.org/docs/reference/coroutines/flow.html) - handle the stream of data asynchronously that executes sequentially.
    * [Jetpack](https://developer.android.com/jetpack)
        * [Lifecycle](https://developer.android.com/topic/libraries/architecture/lifecycle) - perform action when lifecycle state changes.
        * [ViewModel](https://developer.android.com/topic/libraries/architecture/viewmodel) - store and manage UI-related data in a lifecycle conscious way.
        * [Compose](https://developer.android.com/jetpack/compose?gclsrc=ds&gclsrc=ds) - build clean modern android UIs.
        * [Hilt](https://developer.android.com/training/dependency-injection/hilt-android) - dependency injection library for Android that reduces the boilerplate of doing manual dependency
        * [Navigation component](https://developer.android.com/guide/navigation) - perform complex navigation. 

* Architecture
    * MVVM - Model View View Model


### Features under heavy construction

- [ ] Adding social login
- [ ] Implementing search via the API
- [ ] Adding Paging 3
- [ ] Adding payment methods
- [ ] Uploading to Google Playstore
