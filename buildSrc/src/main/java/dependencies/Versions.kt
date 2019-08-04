package dependencies

object Versions {
    val compileSdkVersion = 28
    val minSdkVersion = 21

    private const val versionMajor = 1
    private const val versionMinor = 1
    private const val versionPatch = 0
    private const val versionOffset = 0
    val androidVersionCode =
            (versionMajor * 10000 + versionMinor * 100 + versionPatch) * 100 + versionOffset

    val androidVersionName = "$versionMajor.$versionMinor.$versionPatch"
}