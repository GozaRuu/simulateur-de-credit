# simulateur-de-credit
Android App that calculates the amount of money to pay after a loan given certain settings using company-local SOAP API 

## About
This project is in French. It was done under an internship in BIAT (Popular Tunisan Bank). For the moment, it only works when your phone in connected to the local network in the company.
But all the UI elements are up and running and display fake data by default.
I used the Ksoap2 libraries and this UI element : [SeekArc](https://github.com/neild001/SeekArc).

## Usage
Download the code and open `build.gradle using` Android Studio.
#### For any unexpected bug : 
Both dependency projects are in `/Modules_and_Jars`.
if a certain bug keeps gradle from properly building the project you start a new project **with the same name** include both dependency projects as modules and copy my code from `/app/src/main/` and copy it to the new project.
