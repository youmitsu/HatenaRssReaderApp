package youmeee.co.jp.hatenarssreaderapp.util

/**
 * ViewType
 */
enum class ViewType(val value: Int) {
    ALL(0),
    SOCIAL(1),
    ECONOMICS(2),
    LIFE(3);

    companion object {
        fun fromValue(value: Int): ViewType {
            return values().filter { it.value == value }.firstOrNull() ?: ViewType.ALL
        }
    }
}