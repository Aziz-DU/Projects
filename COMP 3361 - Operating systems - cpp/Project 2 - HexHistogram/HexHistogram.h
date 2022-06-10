// HexHistogram.h Aziz alkhelaiwi project2
#ifndef HHISTOGRAM_H
#define HHISTOGRAM_H
#include <iostream>
#include <map>

class Hhistogram
{
private:
	int valueCount = 0;
	int uniqueCount = 0;
	int lineCount = 0;
	std::map<int, std::string, std::greater<int>> smallestPerLineMap;
	std::map<uint32_t, uint32_t> histoMap;

public:
	Hhistogram(std::string fileName);
	~Hhistogram();
	uint32_t get_value_count();
	uint32_t get_unique_value_count();
	uint32_t get_line_count();
	uint32_t get_smallest_number(uint32_t lineNumber);
	void print();
};
#endif
