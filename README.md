<h1 align="center">Welcome to Decipher Pad 👋</h1>
<p align="center">
  
  <a aria-label="GitHub issues" href="https://github.com/ausaf007/decipher-pad/issues" target="_blank">
    <img src="https://img.shields.io/github/issues/ausaf007/decipher-pad?style=for-the-badge" />
  </a>
  <a aria-label="GitHub license" href="https://github.com/ausaf007/decipher-pad/blob/master/LICENSE" target="_blank">
    <img src= "https://img.shields.io/badge/LICENSE-MIT-brightgreen?style=for-the-badge" />
  </a>
  <a aria-label="Build Status" target="_blank">
    <img src="https://img.shields.io/badge/build-passing-brightgreen?style=for-the-badge" />
  </a>
  <a aria-label="GitHub contributors" href="https://github.com/ausaf007/decipher-pad/graphs/contributors" target="_blank">
    <img src="https://img.shields.io/badge/Contributors-3-brightgreen?style=for-the-badge" />
  </a>
  <a aria-label="linkedin-shield" href="https://www.linkedin.com/in/md-ausaf-rashid/" target="_blank">
    <img src="https://img.shields.io/badge/-LinkedIn-black.svg?style=for-the-badge&logo=linkedin&colorB=555" />
  </a>
</p>

<h3 align="center">Encrypt and secure your text files with Decipher Pad!</h3>

<!-- TABLE OF CONTENTS -->
<details open>
  <summary>Table of Contents</summary>
  <ul>
    <li><a href="#about-the-project">About The Project</a></li>
    <li><a href="#tech-stack">Tech Stack</a></li>
    <li><a href="#prerequisites">Prerequisites</a></li>
    <li><a href="#development-setup">Development Setup</a></li>
    <li><a href="#how to use">How to use?</a></li>
    <li><a href="#license">License</a></li>
  </ul>
</details>

## About The Project

Decipher Pad is a Java-Swing based GUI app that encrypts/decrypts sensitive text files, and protects your privacy. This application uses the inbuilt javax.crypto class to perform common cryptographic operations, and implement password-based encryption with MD5 and DES. 

## Tech Stack

[![](https://img.shields.io/badge/Built_with-Java-red?style=for-the-badge&logo=Java)](https://www.java.com/)
[![](https://img.shields.io/badge/Built_with-Swing%20Framework-red?style=for-the-badge&logo=Java)](https://docs.oracle.com/javase/7/docs/api/javax/swing/package-summary.html)
[![](https://img.shields.io/badge/Built_with-IntelliJ_IDEA-red?style=for-the-badge)](https://www.jetbrains.com/idea/)

## Prerequisites

Download and install [Java 11 SE Development Kit](https://www.oracle.com/java/technologies/downloads/#java11)(or higher) and [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) IDE. You may use any Java IDE of your choice.  

## Development Setup

1. Click on the Fork button to save a copy of this project on your account.
2. Download the repository or clone it using the bash command:
   ``` 
   git clone https://github.com/ausaf007/decipher-pad.git 
   ```
3. Import the project in IntelliJ IDEA or any IDE of your choice.
4. Build and run the application.

## How To Use?

* Navigate to **decipher_pad** package:
   ``` 
   cd src/com/github/decipher_pad
   ```
* Compile Main.java and run it. Then, type the secret message, and go to File -> Encrypt and Save or press Cntrl+Shift+S.
* Choose the saving directory and then enter a new password. 
* To decrypt the file, go to File -> Decrypt, navigate to the file and enter the password. Now the file is successfully decrypted.
* Stay safe and protect your privacy with Decipher Pad! 

## License
Code released under [MIT License.](https://choosealicense.com/licenses/mit/)

