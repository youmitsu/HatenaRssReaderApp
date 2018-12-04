package youmeee.co.jp.hatenarssreaderapp.presentation

interface BindableAdapter<T> {
    fun setData(items: List<T>)
}