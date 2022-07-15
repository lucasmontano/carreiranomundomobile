package com.lucasmontano.openweather

object DummyWeatherForecast {

  private val dummyForecastSet = setOf(
    Forecast(isRaining = true, windSpeed = 1.5F), // light raining
    Forecast(isRaining = false, windSpeed = 10F), // good weather
    Forecast(isRaining = false, windSpeed = 50F), // Netherlands
    Forecast(isRaining = true, windSpeed = 50F), // Netherlands with Rain
  )

  private val cacheCityForecastMap = mutableMapOf<String, Forecast>()

  /**
   * Return random forecast for [city] or [city] cached forecast.
   */
  fun forecast(city: String) = cacheCityForecastMap[city] ?: dummyForecastSet.random()
}
