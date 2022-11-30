package com.app.myPracticalTask.view.pojo

import retrofit2.Response

data class AlbumWithPhotoResponse(
    var pojoAlbum : Response<PojoAlbum>? =null,
    var pojoPhotos : Response<PojoPhotos>?=null,
    val photoList1:PojoAlbum?=null
) {
   // constructor(): this(null,null,null)
    constructor(pojoAlbum : Response<PojoAlbum>?,pojoPhotos : Response<PojoPhotos>?):this(pojoAlbum, pojoPhotos,null)
//    constructor(pojoAlbum: PojoPhotosItem, pojoPhotos: PojoAlbumItem) : this()
}