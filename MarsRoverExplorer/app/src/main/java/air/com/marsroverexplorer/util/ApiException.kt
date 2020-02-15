package air.com.marsroverexplorer.util

import java.io.IOException

class ApiException(message : String, var errorCode : Int) : IOException(message)
class NoInternetException(message: String) : IOException(message)