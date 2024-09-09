<h1 align="center">Current Weather</h1>

<p align="center">  
Current Weather is a small demo application based on modern Android tech-stacks especially focus on Jetpack using Weather API. Also fetching data from the network.
</p>
</br>

<p align="center">
<img src="/previews/preview_1.png" width="350" />
<img src="/previews/preview_2.png" width="350" />
</p>


## How to build on your environment
Add your [OpenWeather](https://openweathermap.org/)'s API key in local.properties file.
```xml
weather_api_key=YOUR_API_KEY
```

<img src="/previews/preview0.gif" align="right" width="32%"/>

## Tech stack & Open-source libraries
- 100% [Kotlin](https://kotlinlang.org/): A modern, expressive, and concise language.
- Jetpack Libraries:
  -  ViewModel: Manages UI-related data in a lifecycle-conscious way.
  - LiveData: Observable data holder class for lifecycle-aware components.
- Architecture:
  - MVVM (Model-View-ViewModel): Separation of concerns with ViewModel handling UI logic.
- Repository Pattern: Encapsulates data logic and business rules.
- Material Design: Provides a consistent, clean UI and UX.
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit): Efficiently handles network operations for making API requests.


# License
```xml
Designed and developed by 2021 hardz (Hardik Kubavat)

        Licensed under the Apache License, Version 2.0 (the "License");
        you may not use this file except in compliance with the License.
        You may obtain a copy of the License at

        http://www.apache.org/licenses/LICENSE-2.0

        Unless required by applicable law or agreed to in writing, software
        distributed under the License is distributed on an "AS IS" BASIS,
        WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
        See the License for the specific language governing permissions and
        limitations under the License.
```

