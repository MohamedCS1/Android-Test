# Android-Test

Application that demonstrates modern Android development based on Clean Architecture

# Attension
For build project add APY_KEY in ( local.properties ) file like below and rebuild project
<pre>
APY_KEY = c9856d0cb57c3f14bf75bdc6c063b8f3
</pre>

APK link -> https://drive.google.com/file/d/1E_b9a-0MPzUc55Wq8HsY8nm6AUmy2Gb9/view?usp=share_link

# Tech stack

- Kotlin : Programming language.
- Clean Architecture: software design approach that emphasizes the separation of concerns in software development by dividing the codebase into layers with specific responsibilities.
- Coroutines : A concurrency design pattern that you can use on Android to simplify code that executes asynchronously.
- Flow : A reactive programming library introduced in Kotlin 1.3. It allows developers to write asynchronous and non-blocking code in a sequential and declarative way.
- ViewModel: Manages UI-related data holder and lifecycle aware. Allows data to survive configuration changes such as screen rotations.
- DataBinding: Binds UI components in your layouts to data sources in your app using a declarative format rather than programmatically.
- Room Database: Constructs Database by providing an abstraction layer over SQLite to allow fluent database access.
- Dagger Hilt: for dependency injection.
- MVVM Architecture (View - DataBinding - ViewModel - Model)
- Retrofit2 & OkHttp3: Construct the REST APIs and paging network data.
- Glide, GlidePalette: Loading and cache images from network.
- Truth : Use in test room database
