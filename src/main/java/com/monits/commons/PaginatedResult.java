/**
 * PaginatedResult Class
 *
 * @copyright 2011 Monits
 * @license   Copyright (C) 2010. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
package com.monits.commons;

import java.util.List;

/**
 * PaginatedResult Class
 *
 * @author    José Manuel Rolón<jmrolon@monits.com>
 * @copyright 2011 Monits
 * @license   Copyright (C) 2011. All rights reserved
 * @version   Release: 1.0.0
 * @link      http://www.monits.com/
 * @since     1.0.0
 */
public class PaginatedResult<T> {

	private int actualPage;

	private int totalPage;

	private List<T> elements;

	 /**
	 * Basic constructor with all fields.
	 *
	 * @param actualPage The Selected page
	 * @param elements The items in the selected page
	 * @param totalElements the total amount of elements
	 * @param amountPerPage the amount the elements per page
	 */
	public PaginatedResult(int actualPage, List<T> elements, int totalElements, int amountPerPage ) {
		super();

		this.actualPage = actualPage;
		this.totalPage = this.calculateTotalPages(totalElements, amountPerPage);
		this.elements = elements;
	}

	/**
	 * @return the acutalPage
	 */
	public int getActualPage() {
		return actualPage;
	}

	/**
	 * @return the totalPage
	 */
	public int getTotalPage() {
		return totalPage;
	}

	/**
	 * @return the elements
	 */
	public List<T> getElements() {
		return elements;
	}

	/**
     * Calculate the total amount of pages, using a amount of elements and the amount of elements per page
     *
     * @param totalElements amount of total elements
     * @param amountPage amount of element per page
     * @return the total amount of pages
     */
    private int calculateTotalPages(int totalElements, int amountPage) {

    	int totalPages;

    	totalPages = totalElements/amountPage;

 		if(totalElements%amountPage != 0) {
 			totalPages++;
 		}
 		return totalPages;
    }

}
