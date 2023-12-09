package ru.dima.aston_intensiv_4.second_task.data




object UsersDB {

    private const val PHOTO_SRC_EXAMPLE = "https://cdn-icons-png.flaticon.com/512/8188/8188349.png"

    var users = MutableList(100){
        User(
            id = it.toString(),
            firstName = "Имя$it",
            lastName = "Фамилия$it",
            phoneNumber = "+ 7 (999) 999-99-99",
            photoSrc = PHOTO_SRC_EXAMPLE
        )
    }
}