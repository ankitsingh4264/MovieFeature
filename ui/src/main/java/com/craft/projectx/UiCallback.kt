package com.craft.projectx

sealed class UiCallback{
    class  AddPackage(val name:String,val isSelected:Boolean) : UiCallback()
}