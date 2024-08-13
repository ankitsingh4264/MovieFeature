package com.craft.projectx.utils

sealed class UiCallback {
    class AddPackage(val name: String, val isSelected: Boolean) : UiCallback()
    class SaveRestrictedTime(val restrictedTime: Long) : UiCallback()

}