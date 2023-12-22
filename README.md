# NusArt (Nusantara Art) - Mobile Development Repository 

Hello everyone, welcome in this Mobile Development repository for NusArt Capstone Project. 

What is this repository about?
As the name implies, this repository is used as a workplace for Mobile Development roles in the Capstone Project as part of Bangkit 2023 Batch 2 Product Based Capstone Project Build in Native Android with Kotlin using Android Studio. 

## This Application built with 

A. UI/UX and Design 
- Material
- OkHttp3
- Glide

B. Image Processing 
- Retrofit
- Camera X

C. Library 
- Fragment
- Google Maps Services

## How to install and get started with the repository : 
You can use this methods below to get and use the application build below:

Clone this repository with using HTTPS:

HTTPS: Use Git or SVN checkout by making use of this URL: https://github.com/Hanzein/NusantaraArt_MD.git
`git clone https://github.com/Hanzein/NusantaraArt_MD.git`

2. By using Android Studio or any other Android software development IDE(s)
- Enable a version control integration in a project, for example in the Android Studio:
   -- By selecting VCS -> Enable Version Control Integration in your Android Studio menubar.
   -- Check if the version control system (VCS) associated to the project is Git. If you are already set to another VCS, set it in the Settings -> Version Control.
- Set a new remote in Git -> Manage Remotes and pressing the 'plus' icon. Set it to the HTTPS link.
- Pull the project files to retrieve all files from this repository (if needed)
- Rebuild or clean the project files.


## Features : 
Here are some feature that i implement on NusArt application : 
- ArtLibrary : This library lists data on paintings in Indonesia from the pre-independence era to the digital and technological era. It also displays paintings from various painting styles and from various Indonesian painters as well as the meaning of the paintings. This feature has been integrated with the API, so we call the list of data in the Cloud. 
- ArtExpo Tracker : This feature is a feature that will detect the user when approaching a museum area that is holding an art exhibition, when the user enters the area where the exhibition is then the user will get a notification (for this feature the user is required to activate location services and allow notifications on his device. 
- Art Identifier :  This feature is a feature to identify paintings and then display the era, style, and genre of the painting. Then, the way it works is when the user presses the floating action button of the camera, the user will be directed to AddActivity, there the user can immediately use camera X, when the user directs the frame to the painting then the image will be processed using ML and the user is directed to the ResulScan Activity to see the results of the painting scan. 
- Localization : This feature is a feature that we provide to change the application language to several languages that we provide, currently there are only 4 languages, namely : Indonesian, English, Japanese, Malaysian. Users can change the language by clicking the Change language button then the user can select the language in the Language Activity.  
- Login dan Register :  Then we also have login and register, so to access this application users are required to log in first, this login and register are integrated with the API. 
