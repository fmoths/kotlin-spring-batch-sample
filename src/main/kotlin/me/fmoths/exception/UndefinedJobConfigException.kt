package me.fmoths.exception

import java.lang.RuntimeException

class UndefinedJobConfigException: RuntimeException {
    constructor()
    constructor(message: String) : super(message)
}