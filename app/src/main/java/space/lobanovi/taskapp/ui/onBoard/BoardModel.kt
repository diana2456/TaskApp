package space.lobanovi.taskapp.ui.onBoard

import java.io.Serializable

data class BoardModel(
    var  img : Int? = null,
    var title : String? = null,
    var description : String? = null,
    var isLast: Boolean? = false
): Serializable
