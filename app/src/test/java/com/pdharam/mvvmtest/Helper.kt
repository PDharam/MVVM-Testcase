package com.pdharam.mvvmtest

import java.io.InputStreamReader

object Helper {

    val productJson = "[\n" +
            "  {\n" +
            "    \"id\": 1,\n" +
            "    \"title\": \"Test Product\",\n" +
            "    \"price\": 210.50,\n" +
            "    \"description\": \"Test Description\",\n" +
            "    \"category\": \"men's clothing\",\n" +
            "    \"image\": \"https://fakestoreapi.com/img/81fPKd-2AYL._AC_SL1500_.jpg\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"id\": 2,\n" +
            "    \"title\": \"Mens T-Shirts \",\n" +
            "    \"price\": 25.30,\n" +
            "    \"description\": \"Three-button t shirt.\",\n" +
            "    \"category\": \"men's clothing\",\n" +
            "    \"image\": \"https://fakestoreapi.com/img/71li-ujtlUL._AC_UX679_.jpg\"\n" +
            "  }\n" +
            "]";

    fun readFileResource(filename: String): String {
        val inputStream = Helper::class.java.getResourceAsStream(filename)
        val builder = StringBuilder()
        val reader = InputStreamReader(inputStream, "UTF-8")
        reader.readLines().forEach {
            builder.append(it)
        }
        return builder.toString()
    }
}