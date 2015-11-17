# Porchlight
Porchlight is an Android app created by [Location Labs][1] for the [AT&T Mobile App Hackathon - New York City][2] that occured on November 6, 2015. It won the prizes of the "Best Overall App" and "Crush on Apps - Location Services". 

## Is it the project that was created during the hackathon?
Yes it is. But I've cleaned the code a little bit and added a mock mode so the app can work without backend. We were using a flask app with redis running on a laptop as backend. Now that the hackathon is over, there is no plan to keep a backend running. However, I included the non-mocked code too, so you can see what was done at that time. It won't work though, unless you implement a backend yourself.

## What technologies and libraries were used?
* Android client
    1. Google Play Services (Maps)
    2. ButterKnife (Field and method binding for Android views)
    3. Volley (Retrofit would have been better)
    4. Gson
    5. PubNub (Real-time notifications)
* Backend
    1. Flask
    2. Redis
    3. Alcatel-Lucent IMS [Call Direction Rest API][3] (Available during the Hackathon)

## Who was on the team for the hackathon?
We were four people from [Location Labs][1]: a project manager, a product manager/designer, a backend engineer and a frontend engineer.

[1]: http://locationlabs.com/
[2]: http://www.eventbrite.com/e/att-mobile-app-hackathon-new-york-city-tickets-18849801263
[3]: http://developer-ims.alcatel-lucent.com/

## License
```
Copyright 2015 Julien Biral

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
