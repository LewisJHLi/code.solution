package com.solution.service.data;

import com.solution.model.CompanyResource;
import com.solution.model.Record;
import com.solution.model.barcode.Barcode;
import com.solution.model.catalog.Catalog;
import com.solution.model.supplier.Supplier;
import com.solution.service.file.CsvFileReader;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DataCleaner {
	private final CsvFileReader csvFileReader;
	private final String INPUT_DIR = "./input/";

	/**
	 * Default Constructor.
	 */
	public DataCleaner() {
		this.csvFileReader = CsvFileReader.builder();
	}

	public DataCleaner(final CsvFileReader csvFileReader) {
		this.csvFileReader = csvFileReader;
	}

	/**
	 * Returns a new builder that is capable of producing a {@link DataCleaner} instance.
	 *
	 * @return DataCleaner Builder class.
	 */
	public static DataCleaner builder(){
		return new DataCleaner();
	}

	/**
	 * Validate the input files and pass the file data to the handler.
	 *
	 * @param files
	 *      List of input files.
	 * @return
	 *      A {@link CompanyResource} instance with clean data.
	 * @throws Exception
	 *      Error reading files.
	 */
	public CompanyResource getCleanData(final String withCompany, final List<String> files) throws Exception {
		List<Catalog> rawCatalogs = null;
		List<Barcode> rawBarcodes = null;
		List<Supplier> rawSuppliers = null;

		for (String file : files) {
			List<String[]> fileData = this.csvFileReader.readFile(file);
			if (fileData.size() > 0) {
				if (file.equalsIgnoreCase(INPUT_DIR + "catalog" + withCompany + ".csv")) {
					rawCatalogs = fileData.stream()
							.map(row -> new Catalog(row[0], row[1]))
							.collect(Collectors.toList());
				} else if (file.equalsIgnoreCase(INPUT_DIR + "barcodes" + withCompany + ".csv")) {
					rawBarcodes = fileData.stream()
							.map(row -> new Barcode(row[0], row[1], row[2]))
							.collect(Collectors.toList());
				} else if (file.equalsIgnoreCase(INPUT_DIR + "suppliers" + withCompany + ".csv")) {
					rawSuppliers = fileData.stream()
							.map(row -> new Supplier(row[0], row[1]))
							.collect(Collectors.toList());
				} else {
					throw new Exception("Cannot find file \"" + file + "\". Please refer to the read me for more info.");
				}
			} else {
				throw new Exception("no records found for file" + file);
			}
		}

		return cleanRawData(withCompany, rawCatalogs, rawBarcodes, rawSuppliers);
	}

	/**
	 * Handle the raw data from input files.
	 *
	 * @param withCompany
	 *      Company name the resource belongs to.
	 * @param rawCatalogs
	 *      Raw catalog data from the input file.
	 * @param rawBarcodes
	 *      Raw barcode data from the input file.
	 * @param rawSuppliers
	 *      Raw supplier data from the input file.
	 * @return
	 *      A {@link CompanyResource} instance with clean data.
	 */
	private CompanyResource cleanRawData(String withCompany, List<Catalog> rawCatalogs, List<Barcode> rawBarcodes, List<Supplier> rawSuppliers) {
		final List<Catalog> cleanCatalog = new ArrayList<>();
		final List<Record> cleanRecord = new ArrayList<>();

		for(Barcode connectBarcode:rawBarcodes) {
			String barcodeSku = connectBarcode.getSku();
			String barcodeSupplierId = connectBarcode.getSupplierId();

			Optional<Catalog> optCatalog = rawCatalogs.stream()
					.filter(catalog -> catalog.getSku().equalsIgnoreCase(barcodeSku))
					.findFirst();
			Optional<Supplier> optSupplier = rawSuppliers.stream()
					.filter(supplier -> supplier.getId().equalsIgnoreCase(barcodeSupplierId))
					.findFirst();

			if (optCatalog.isPresent() && optSupplier.isPresent()) {
				Catalog connectCatalog = optCatalog.get();
				Supplier connectSupplier = optSupplier.get();

				cleanCatalog.add(connectCatalog);
				cleanRecord.add(new Record(connectCatalog, connectBarcode, connectSupplier));
			}
		}
		return new CompanyResource(withCompany, cleanCatalog, cleanRecord);
	}
}
