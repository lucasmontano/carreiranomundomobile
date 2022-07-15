package com.lucasmontano.openweather

object DummyWeatherForecast {

  private val dummyForecastSet = setOf(
    Forecast(isRaining = true, windSpeed = 1.5F), // light raining
    Forecast(isRaining = false, windSpeed = 10F), // good weather
    Forecast(isRaining = false, windSpeed = 50F), // Netherlands
    Forecast(isRaining = true, windSpeed = 50F), // Netherlands with Rain
  )

  private val cacheCityForecastMap = mutableMapOf<String, Forecast>()

  var currentCity: String? = null

  /**
   * Return random forecast for [currentCity] or [currentCity] cached forecast.
   */
  fun forecast() = cacheCityForecastMap[currentCity] ?: dummyForecastSet.random()
}
