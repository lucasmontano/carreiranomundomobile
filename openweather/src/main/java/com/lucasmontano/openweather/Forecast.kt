package com.lucasmontano.openweather

/**
 * Weather Forecast Simple Data.
 *
 * @param windSpeed Wind Speed in Km/h
 * @param isRaining TRUE if it's raining right now
 */
data class Forecast(val windSpeed: Float, val isRaining: Boolean)
