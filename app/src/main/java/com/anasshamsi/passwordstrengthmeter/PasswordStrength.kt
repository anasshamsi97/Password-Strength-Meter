package com.anasshamsi.passwordstrengthmeter

class PasswordStrength {

    var msg: Int = 0
    var color: Int = 0
    private val MIN_LENGTH = 8
    private val MAX_LENGTH = 15


    fun calculate(password: String): Int {
        var score = 0
        // boolean indicating if password has an upper case
        var upper = false
        // boolean indicating if password has a lower case
        var lower = false
        // boolean indicating if password has at least one digit
        var digit = false
        // boolean indicating if password has a leat one special char
        var specialChar = false

        for (i in 0 until password.length) {
            val c = password[i]

            if (!specialChar && !Character.isLetterOrDigit(c)) {
                score++
                specialChar = true
            } else {
                if (!digit && Character.isDigit(c)) {
                    score++
                    digit = true
                } else {
                    if (!upper || !lower) {
                        if (Character.isUpperCase(c)) {
                            upper = true
                        } else {
                            lower = true
                        }

                        if (upper && lower) {
                            score++
                        }
                    }
                }
            }
        }

        val length = password.length

        if (length > MAX_LENGTH) {
            score++
        } else if (length < MIN_LENGTH) {
            score = 0
        }

        // return enum following the score
        when (score) {
            0 -> return R.string.weak
            1 -> return R.string.medium
            2 -> return R.string.strong
            3 -> return R.string.very_strong
        }

        return R.string.very_strong
    }

}