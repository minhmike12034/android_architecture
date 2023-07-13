# Clean Architecture for both XML and Jetpack compose

## Sample apps using data from [Themovieapi](https://www.themoviedb.org/).
This project has 3 apps:
1) Movie App using XML (left)
2) Movie App using Jetpack Compose (right) 

![movie_app](https://github.com/minhmike12034/android_architecture/assets/116247232/ea9c814f-91ef-4ce8-ae82-4b0960671d82)
![compose](https://github.com/minhmike12034/android_architecture/assets/116247232/9d42f8a6-3d24-49c6-aae7-a9879858585f)

3) App Theme - showcase for Design module

![light_theme](https://github.com/minhmike12034/android_architecture/assets/116247232/0800bb53-712f-4630-b7e4-a87cfe24f65c)
![dark_theme](https://github.com/minhmike12034/android_architecture/assets/116247232/3de68d4f-1c66-487e-ad79-4d96bc7687c4)


### Features of app
- Login ( validate user name and password )
- List paging movie
- Movie detail ( offline mode )

![login_page](https://github.com/minhmike12034/android_architecture/assets/116247232/bb239e57-3e88-4f02-b9fa-2c0c4b4cf201)
![movie_detail](https://github.com/minhmike12034/android_architecture/assets/116247232/59b62ab4-f633-4fe8-839c-15f787067aed)
![compose](https://github.com/minhmike12034/android_architecture/assets/116247232/d99f7660-369e-4390-90a3-0516729c9145)


## Clean Architecture
Clean Architecture combines a group of practices that produce systems with the following characteristics:

- Testable
- UI-independent (the UI can easily be changed without changing the system)
- Independent of databases, frameworks, external agencies, and libraries
[Source](https://rubygarage.org/blog/clean-android-architecture)
### Presentation layer
![clean_architecture](https://github.com/minhmike12034/android_architecture/assets/116247232/c7253059-fff8-4ef0-9384-88a6fe36de9d)


- Activity / Fragment or Jetpack Compose
- ViewModel
- Handle view logic , store view state, display view
- It doesn’t care anything relate to business logic  and where the data come from

### Domain layer
- Use case 
- Data Entities, Error Entities
- Handle business logic
- Get data from Data layer, and it doesn’t care about where the data come from

Object naming
![object_naming](https://github.com/minhmike12034/android_architecture/assets/116247232/13de3274-a692-4748-b596-e13cafca4054)

### Data layer
- Repository
- Local data source, remote data source 
- Handle data logic such as mapping data, cache data

### Handle error
- Data layer will handle **data error** like `DatabaseError and NetworkError`
- Domain layer will handle **business error** like `WrongEmailPattern` or `PasswordIsNotEnoughLength`
- In the end, when debug, we will easily to know where does the error come from.

## Jetpack compose
Design module is independent and can import to any modules, it contains 
- Theme
- Typo
- Color
- Custom component like: button, text, edittext

After creating Design module, we can import Design module to
- AppTheme to show case
- Movie App
- Movie App Lite

![design_module](https://github.com/minhmike12034/android_architecture/assets/116247232/8a597b6c-301f-40b0-8935-1c44ee30aef3)


### Reuse Domain layer and Data layer
Let's migrate App xml to Jetpack compose.

Now we have:
- Presentation: app xml
- Domain: **business logic**  (validate user and password in LoginUseCase)
- Data: **data logic** (get data from themovieapi, cache by using Room database)

Because of each layer is independent 

So we only need migrate **Presetation layer**: XML -> Jetpack Compose. 

We can reuse **Domain** and **Data** without any concern relate to **business logic and data logic**

![reuse_domain_data](https://github.com/minhmike12034/android_architecture/assets/116247232/234eec39-922a-4d81-9094-83a7ff4a3bec)

## Testing

### Repository test
Not yet
### Usecase test
Not yet
### Compose UI Test

<img width="700" alt="compose_ui_test" src="https://github.com/minhmike12034/android_architecture/assets/116247232/ebf22537-7a80-427f-bd60-ecb519adea77">

## API key
[Link](https://drive.google.com/file/d/1eOA7MDf91uDyIPjn7kANO9VsqAjv6whG/view?usp=drive_link)

It should be same level of `app`

