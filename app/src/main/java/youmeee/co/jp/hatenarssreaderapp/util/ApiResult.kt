package youmeee.co.jp.hatenarssreaderapp.util

/**
 * Created by yumitsuhori on 2018/12/05.
 */
sealed class ApiResult<V>(open val value: V)

data class SUCCESS<V>(override val value: V) : ApiResult<V>(value)
data class FAILED<V>(override val value: V, val reason: Exception) : ApiResult<V>(value)
