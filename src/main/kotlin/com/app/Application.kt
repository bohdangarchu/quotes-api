package com.app

import io.micronaut.runtime.Micronaut
import io.micronaut.runtime.Micronaut.run
fun main(args: Array<String>) {
	Micronaut.build()
		.args(*args)
		.packages("com.app")
		.start()
}

