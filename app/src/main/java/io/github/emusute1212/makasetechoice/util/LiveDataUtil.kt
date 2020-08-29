package io.github.emusute1212.makasetechoice.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.distinctUntilChanged

/**
 * https://qiita.com/m-coder/items/000dbfc1c90d734eae72
 *
 * @param T
 * @param LIVE1
 * @param LIVE2
 * @param initialValue
 * @param liveData1
 * @param liveData2
 * @param block
 * @return
 */
inline fun <T : Any, LIVE1 : Any, LIVE2 : Any> combine(
    initialValue: T,
    liveData1: LiveData<LIVE1>,
    liveData2: LiveData<LIVE2>,
    crossinline block: (T, LIVE1, LIVE2) -> T
): LiveData<T> {
    return MediatorLiveData<T>().apply {
        value = initialValue
        listOf(liveData1, liveData2).forEach { liveData ->
            addSource(liveData) {
                val currentValue = value
                val liveData1Value = liveData1.value
                val liveData2Value = liveData2.value
                if (currentValue != null && liveData1Value != null && liveData2Value != null) {
                    value = block(currentValue, liveData1Value, liveData2Value)
                }
            }
        }
    }.distinctUntilChanged()
}


inline fun <T : Any, LIVE1 : Any, LIVE2 : Any, LIVE3 : Any> combine(
    initialValue: T,
    liveData1: LiveData<LIVE1>,
    liveData2: LiveData<LIVE2>,
    liveData3: LiveData<LIVE3>,
    crossinline block: (T, LIVE1, LIVE2, LIVE3) -> T
): LiveData<T> {
    return MediatorLiveData<T>().apply {
        value = initialValue
        listOf(liveData1, liveData2, liveData3).forEach { liveData ->
            addSource(liveData) {
                val currentValue = value
                val liveData1Value = liveData1.value
                val liveData2Value = liveData2.value
                val liveData3Value = liveData3.value
                if (currentValue != null && liveData1Value != null &&
                    liveData2Value != null && liveData3Value != null
                ) {
                    value = block(currentValue, liveData1Value, liveData2Value, liveData3Value)
                }
            }
        }
    }.distinctUntilChanged()
}

inline fun <T : Any, LIVE1 : Any, LIVE2 : Any, LIVE3 : Any, LIVE4 : Any> combine(
    initialValue: T,
    liveData1: LiveData<LIVE1>,
    liveData2: LiveData<LIVE2>,
    liveData3: LiveData<LIVE3>,
    liveData4: LiveData<LIVE4>,
    crossinline block: (T, LIVE1, LIVE2, LIVE3, LIVE4) -> T
): LiveData<T> {
    return MediatorLiveData<T>().apply {
        value = initialValue
        listOf(liveData1, liveData2, liveData3, liveData4).forEach { liveData ->
            addSource(liveData) {
                val currentValue = value
                val liveData1Value = liveData1.value
                val liveData2Value = liveData2.value
                val liveData3Value = liveData3.value
                val liveData4Value = liveData4.value
                if (currentValue != null && liveData1Value != null &&
                    liveData2Value != null && liveData3Value != null &&
                    liveData4Value != null
                ) {
                    value = block(
                        currentValue,
                        liveData1Value,
                        liveData2Value,
                        liveData3Value,
                        liveData4Value
                    )
                }
            }
        }
    }.distinctUntilChanged()
}