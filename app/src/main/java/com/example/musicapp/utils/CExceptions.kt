package com.example.musicapp.utils

class NullResponse(message: String = " Response was null"): Exception(message)
class FailResponse(message: String?): Exception(message)