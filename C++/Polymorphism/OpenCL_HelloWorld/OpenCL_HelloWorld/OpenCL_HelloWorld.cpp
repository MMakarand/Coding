// OpenCL_HelloWorld.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"

//const std::string hw("Hello World\n");

inline void checkErr(cl_int err, const char* name)
{
	if (err != CL_SUCCESS) {
		std::cerr << "ERROR: " << name << " (" << err << ")" << std::endl;
		exit(EXIT_FAILURE);
	}

}


int main()
{
	cl_int err;

	std::vector<cl::Platform> platformList;
	cl::Platform::get(&platformList);
	checkErr(platformList.size() != 0 ? CL_SUCCESS : -1, "cl::Platform::get");
	std::cerr << "Platform number is: " << platformList.size() << std::endl;

	/*std::string platformVendor;
	platformList[0].getInfo((cl_platform_info)CL_PLATFORM_VENDOR, &platformVendor);
	std::cerr << "Platform is by: " << platformVendor << "\n";
	cl_context_properties cprops[3] =
	{ CL_CONTEXT_PLATFORM, (cl_context_properties)(platformList[0])(), 0 };

	cl::Context context(
		CL_DEVICE_TYPE_CPU,
		cprops,
		NULL,
		NULL,
		&err);
	checkErr(err, "Conext::Context()");
	*/
	cl::Platform default_platform = platformList[0];
	std::cout << "Using platform: " << default_platform.getInfo<CL_PLATFORM_NAME>() << "\n";


	//char * outH = new char[hw.length() + 1];
	/*cl::Buffer outCL(
		context,
		CL_MEM_WRITE_ONLY | CL_MEM_USE_HOST_PTR,
		hw.length() + 1,
		outH,
		&err);
	checkErr(err, "Buffer::Buffer()");
	*/
	
	std::vector<cl::Device> devices;
	default_platform.getDevices(CL_DEVICE_TYPE_ALL,&devices);

	if (devices.size() == 0)
	{
		std::cout << " No devices found. Check OpenCL installation!\n";
		exit(1);
	}
	cl::Device default_device = devices[0];
	std::cout << "Using device: " << default_device.getInfo<CL_DEVICE_NAME>() << "\n";
	//devices = context.getInfo<CL_CONTEXT_DEVICES>();
	
	checkErr(
		devices.size() > 0 ? CL_SUCCESS : -1, "devices.size() > 0");

	//std::ifstream file("lesson1_kernels.cl");
	//checkErr(file.is_open() ? CL_SUCCESS : -1, "lesson1_kernel.cl");

	/*
	std::string prog(
		std::istreambuf_iterator<char>(file),
		(std::istreambuf_iterator<char>()));

	cl::Program::Sources source(

		1,
		std::make_pair(prog.c_str(), prog.length() + 1));

	cl::Program program(context, source);
	err = program.build(devices, "");
	checkErr(file.is_open() ? CL_SUCCESS : -1, "Program::build()");

	cl::Kernel kernel(program, "hello", &err);
	checkErr(err, "Kernel::Kernel()");

	err = kernel.setArg(0, outCL);
	checkErr(err, "Kernel::setArg()");

	cl::CommandQueue queue(context, devices[0], 0, &err);
	checkErr(err, "CommandQueue::CommandQueue()");


	cl::Event event;
	err = queue.enqueueNDRangeKernel(
		kernel,
		cl::NullRange,
		cl::NDRange(hw.length() + 1),
		cl::NDRange(1, 1),
		NULL,
		&event);
	checkErr(err, "ComamndQueue::enqueueNDRangeKernel()");

	event.wait();
	err = queue.enqueueReadBuffer(
		outCL,
		CL_TRUE,
		0,
		hw.length() + 1,
		outH);
	checkErr(err, "ComamndQueue::enqueueReadBuffer()");
	std::cout << outH;
	return EXIT_SUCCESS;
	*/

	cl::Context context({ default_device });

	cl::Program::Sources sources;
	std::string kernel_code =
		"   void kernel simple_add(global const int* A, global int* C){       "
		"       C[get_global_id(0)]=A[get_global_id(0)]*A[get_global_id(0)];                 "
		"   }                                                                               ";

	sources.push_back({ kernel_code.c_str(),kernel_code.length() });
	cl::Program program(context, sources);
	if (program.build({ default_device }) != CL_SUCCESS) {
		std::cout << " Error building: " << program.getBuildInfo<CL_PROGRAM_BUILD_LOG>(default_device) << "\n";
		exit(1);
	}
	// create buffers on the device
	cl::Buffer buffer_A(context, CL_MEM_READ_WRITE, sizeof(int) * 10);
	cl::Buffer buffer_C(context, CL_MEM_READ_WRITE, sizeof(int) * 10);

	int A[] = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 };
	cl::CommandQueue queue(context, default_device);

	queue.enqueueWriteBuffer(buffer_A, CL_TRUE, 0, sizeof(int) * 10, A);

	//run the kernel
	//cl::KernelFunctor simple_add(cl::Kernel(program, "simple_add"), queue, cl::NullRange, cl::NDRange(10), cl::NullRange);
	cl::Kernel simple_add(program, "simple_add");

	simple_add.setArg(0, buffer_A);
	simple_add.setArg(1, buffer_C);

	queue.enqueueNDRangeKernel(simple_add, cl::NullRange, cl::NDRange(10), cl::NullRange);
	queue.finish();

	int C[10];
	queue.enqueueReadBuffer(buffer_C, CL_TRUE, 0, sizeof(int) * 10, C);
	std::cout << " result: \n";
	for (int i = 0; i<10; i++) {
		std::cout << C[i] << " ";
	}

	getchar();
	return 0;
}

