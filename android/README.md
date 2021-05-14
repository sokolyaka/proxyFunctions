```gradle
//def githubProperties = new Properties() githubProperties.load(new FileInputStream(rootProject.file('github.properties')))
repositories {
    maven {
        url "https://maven.pkg.github.com/sokolyaka/proxyFunctions"
        credentials {
            /** Create github.properties in root project folder file with
             ** gpr.usr=GITHUB_USER_ID & gpr.key=PERSONAL_ACCESS_TOKEN
             ** Or set env variable GPR_USER & GPR_API_KEY if not adding a properties file
             * {@link https://proandroiddev.com/publishing-android-libraries-to-the-github-package-registry-part-1-7997be54ea5a}**/

            username = System.getenv("GPR_USER") /*?: githubProperties['gpr.usr']*/
            password = System.getenv("GPR_API_KEY") /*?: githubProperties['gpr.key']*/
        }
    }
}
```

```gradle
dependencies {
    implementation 'com.github.sokolyaka.proxyFunctions:android:1.0.3'
}
```
