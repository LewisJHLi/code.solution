package com.solution.service.data;

import com.solution.model.CompanyResource;
import com.solution.model.CsvOutput;
import com.solution.model.Record;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class ResourceGenerator {

	private final DataCleaner dataCleaner;
//	private final String HEADQUARTER_NAME = "A";
//	private final String MERGED_NAME = "B";

	/**
	 * Default Constructor.
	 */
	public ResourceGenerator() {
		this.dataCleaner = DataCleaner.builder();
	}

	/**
	 * Returns a new builder that is capable of producing a {@link ResourceGenerator} instance.
	 *
	 * @return ResourceGenerator Builder class.
	 */
	public static ResourceGenerator builder(){
		return new ResourceGenerator();
	}

	/**
	 * Generate the csv output file.
	 *
	 * @return
	 *      void
	 */
	public List<CsvOutput> generate(Map<String, String> companies) throws Exception {
		try {
			Iterator<Map.Entry<String, List<String>>> companyFiles = this.getCompanyAndFiles(companies).entrySet().iterator();

			// Create resources from the input files.
			CompanyResource headquarterResource = this.getResources(companyFiles.next());
			CompanyResource mergedResource = this.getResources(companyFiles.next());

			return getCsvOutputs(headquarterResource, mergedResource);
		} catch (Exception e) {
			throw new Exception("Unable to generate output. Error: " + Arrays.toString(e.getStackTrace()));
		}
	}

	/**
	 * @param headquarterResource
	 *      Resources from the headquarter.
	 * @param mergedResource
	 *      Resources from the merged company
	 * @return
	 *      A list of csv output data.
	 */
	private List<CsvOutput> getCsvOutputs(CompanyResource headquarterResource, CompanyResource mergedResource){
		List<Record> headquarterRecords = headquarterResource.getRecords();
		List<Record> mergedRecords = mergedResource.getRecords();

		// Resources only owned by the headquarter company.
		List<Record> uniqueHeadquarterRecords= headquarterRecords
				.stream()
				.filter(unique ->  !mergedRecords.contains(unique))
				.collect(Collectors.toList());
		// Resources by the both companies.
		List<Record> shareRecords = headquarterRecords
				.stream()
				.filter(mergedRecords::contains)
				.collect(Collectors.toList());
		// Resources only owned by the merged company.
		List<Record> uniqueMergedRecords = mergedRecords
				.stream()
				.filter(unique ->  !headquarterRecords.contains(unique))
				.collect(Collectors.toList());

		// Add all found resources into the csv output.
		Set<CsvOutput> csvOutputs = new HashSet<>();
		csvOutputs.addAll(uniqueHeadquarterRecords.stream()
				.map(record -> new CsvOutput(record.getCatalog().getSku(),
						record.getCatalog().getDescription(),
						headquarterResource.getCompany()))
				.collect(Collectors.toList()));
		csvOutputs.addAll(shareRecords.stream()
				.map(record -> new CsvOutput(record.getCatalog().getSku(),
						record.getCatalog().getDescription(),
						headquarterResource.getCompany()))
				.collect(Collectors.toList()));
		csvOutputs.addAll(uniqueMergedRecords.stream()
				.map(record -> new CsvOutput(record.getCatalog().getSku(),
						record.getCatalog().getDescription(),
						mergedResource.getCompany()))
				.collect(Collectors.toList()));

		return new ArrayList<>(csvOutputs);
	}

	/**
	 * @param files
	 *      Files with company name key.
	 * @return
	 *      Clean data based on the import data.
	 */
	private CompanyResource getResources(Map.Entry<String, List<String>> files) throws Exception {
		return this.dataCleaner.getCleanData(files.getKey(), files.getValue());
	}

	/**
	 * @param companies
	 *      Name of the companies.
	 * @return
	 *      List of file names with company.
	 */
	private Map<String, List<String>> getCompanyAndFiles(Map<String, String> companies) {
		Map<String, List<String>> companyFiles = new HashMap<>();

		for (Map.Entry<String, String> company : companies.entrySet()) {
			List<String> files = new ArrayList<>();
			String name = company.getValue();
			files.add("./input/catalog" + name + ".csv");
			files.add("./input/barcodes" + name + ".csv");
			files.add("./input/suppliers" + name + ".csv");

			companyFiles.put(name, files);
		}

		return companyFiles;
	}
}
