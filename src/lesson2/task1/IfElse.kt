@file:Suppress("UNUSED_PARAMETER")

package lesson2.task1

import lesson1.task1.discriminant
import lesson1.task1.sqr

import java.lang.Math.*

/**
 * Пример
 *
 * Найти наименьший корень биквадратного уравнения ax^4 + bx^2 + c = 0
 */
fun minBiRoot(a: Double, b: Double, c: Double): Double {
    // 1: в главной ветке if выполняется НЕСКОЛЬКО операторов
    if (a == 0.0) {
        if (b == 0.0) return Double.NaN // ... и ничего больше не делать
        val bc = -c / b
        if (bc < 0.0) return Double.NaN // ... и ничего больше не делать
        return -Math.sqrt(bc)
        // Дальше функция при a == 0.0 не идёт
    }
    val d = discriminant(a, b, c)   // 2
    if (d < 0.0) return Double.NaN  // 3
    // 4
    val y1 = (-b + Math.sqrt(d)) / (2 * a)
    val y2 = (-b - Math.sqrt(d)) / (2 * a)
    val y3 = Math.max(y1, y2)       // 5
    if (y3 < 0.0) return Double.NaN // 6
    return -Math.sqrt(y3)           // 7
}

/**
 * Простая
 *
 * Мой возраст. Для заданного 0 < n < 200, рассматриваемого как возраст человека,
 * вернуть строку вида: «21 год», «32 года», «12 лет».
 */
fun ageDescription(age: Int): String {
    return when {
        age % 10 in 5..9 || age % 10 == 0 || age % 100 in 11..19 -> "$age лет"
        age % 10 == 1 && age % 100 != 11 -> "$age год"
        else -> "$age года"
    }
}


/**
 * Простая
 *
 * Путник двигался t1 часов со скоростью v1 км/час, затем t2 часов — со скоростью v2 км/час
 * и t3 часов — со скоростью v3 км/час.
 * Определить, за какое время он одолел первую половину пути?
 */
fun timeForHalfWay(t1: Double, v1: Double,
                   t2: Double, v2: Double,
                   t3: Double, v3: Double): Double {
    val distance1 = t1 * v1
    val distance2 = t2 * v2
    val distance3 = t3 * v3
    val halfWay = (distance1 + distance2 + distance3) / 2.0
    return when {
        (halfWay <= distance1) -> (halfWay / v1)
        (halfWay in distance1..(distance1 + distance2)) -> (t1 + (halfWay - distance1) / v2)
        else -> (t1 + t2 + (halfWay - distance1 - distance2) / v3)
    }
}

/**
 * Простая
 *
 * Нa шахматной доске стоят черный король и две белые ладьи (ладья бьет по горизонтали и вертикали).
 * Определить, не находится ли король под боем, а если есть угроза, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от первой ладьи, 2, если только от второй ладьи,
 * и 3, если угроза от обеих ладей.
 * Считать, что ладьи не могут загораживать друг друга
 */
fun whichRookThreatens(kingX: Int, kingY: Int,
                       rookX1: Int, rookY1: Int,
                       rookX2: Int, rookY2: Int): Int {
    val rook1Treatens = kingX == rookX1 || kingY == rookY1
    val rook2Treatens = kingX == rookX2 || kingY == rookY2
    return when {
        rook1Treatens && rook2Treatens  -> 3
        rook1Treatens -> 1
        rook2Treatens -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * На шахматной доске стоят черный король и белые ладья и слон
 * (ладья бьет по горизонтали и вертикали, слон — по диагоналям).
 * Проверить, есть ли угроза королю и если есть, то от кого именно.
 * Вернуть 0, если угрозы нет, 1, если угроза только от ладьи, 2, если только от слона,
 * и 3, если угроза есть и от ладьи и от слона.
 * Считать, что ладья и слон не могут загораживать друг друга.
 */
fun rookOrBishopThreatens(kingX: Int, kingY: Int,
                          rookX: Int, rookY: Int,
                          bishopX: Int, bishopY: Int): Int {
    val bishopTreatens = abs(bishopX - kingX) == abs(bishopY - kingY)
    val rookTreatens = kingX == rookX || kingY == rookY
    return when {
        rookTreatens && bishopTreatens  -> 3
        rookTreatens -> 1
        bishopTreatens -> 2
        else -> 0
    }
}

/**
 * Простая
 *
 * Треугольник задан длинами своих сторон a, b, c.
 * Проверить, является ли данный треугольник остроугольным (вернуть 0),
 * прямоугольным (вернуть 1) или тупоугольным (вернуть 2).
 * Если такой треугольник не существует, вернуть -1.
 */
fun triangleKind(a: Double, b: Double, c: Double): Int {
    return if (a + b < c || a + c < b || b + c < a) -1
    else {
        val sumOfSides = a + b + c
        val maxSide = max(a, max(b, c))
        val minSide = min(a, min(b, c))
        val thirdSide = sumOfSides - maxSide - minSide
        return when {
            sqr(maxSide) < sqr(minSide) + sqr(thirdSide) -> 0
            sqr(maxSide) == sqr(minSide) + sqr(thirdSide) -> 1
            else -> 2
        }
    }
}

/**
 * Средняя
 *
 * Даны четыре точки на одной прямой: A, B, C и D.
 * Координаты точек a, b, c, d соответственно, b >= a, d >= c.
 * Найти длину пересечения отрезков AB и CD.
 * Если пересечения нет, вернуть -1.
 */
fun segmentLength(a: Int, b: Int, c: Int, d: Int): Int {
    return when {
        (c in a..b) && (d in a..b) -> d - c //если отрезок CD лежит внутри AB
        (a in c..d) && (b in c..d) -> b - a //если отрезок AB лежит внутри CD
        (c in a..b) && d > b -> b - c //если отрезки пересекаются, при этом C лежит в AB
        (a in c..d) && b > d -> d - a //если открезки пересекаются, при этом A лежит в CD
        else -> -1
    }
}
