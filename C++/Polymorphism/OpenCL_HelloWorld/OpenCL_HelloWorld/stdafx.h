// stdafx.h : include file for standard system include files,
// or project specific include files that are used frequently, but
// are changed infrequently
//

#pragma once
#define CL_USE_DEPRECATED_OPENCL_1_2_APIS
#pragma comment (lib, "OpenCL.lib")
#include "targetver.h"

#include <stdio.h>
#include <tchar.h>
#include <utility>

#define _NO_STD_VECTOR // using cl::vector

#include "C:/Intel/OpenCL/sdk/include/CL/cl.hpp"
//#include "cl.hpp"

#include <fstream>
#include <iostream>
#include <string>
#include <iterator>

// TODO: reference additional headers your program requires here
