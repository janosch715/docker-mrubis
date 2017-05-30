package de.hpi.sam.rubis.itemmgmt.impl;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import de.hpi.sam.rubis.entity.Category;
import de.hpi.sam.rubis.entity.Item;
import de.hpi.sam.rubis.itemmgmt.BrowseCategoriesService;
import de.hpi.sam.rubis.itemmgmt.BrowseCategoriesServiceException;
import de.hpi.sam.rubis.queryservice.BasicQueryService;
import de.hpi.sam.rubis.queryservice.QueryService;
import de.hpi.sam.rubis.queryservice.QueryServiceException;

/**
 * Implementation of the {@link BrowseCategoriesService}.
 * 
 * @author thomas
 * 
 */
@Stateless(mappedName = BrowseCategoriesService.MAPPED_NAME)
public class BrowseCategoriesServiceBean implements BrowseCategoriesService {

	@EJB(mappedName = QueryService.MAPPED_NAME)
	private QueryService queryService;

	@EJB(mappedName = BasicQueryService.MAPPED_NAME)
	private BasicQueryService basicQueryService;

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Category> getAllCategories()
			throws BrowseCategoriesServiceException {

		try {
			List<Category> allCategories = this.basicQueryService
					.findAllCategories();
			return allCategories;
		} catch (QueryServiceException e) {
			throw new BrowseCategoriesServiceException(
					"Error in retrieving all categories.", e);
		}

	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Category> getCategoriesInRegion(String regionName)
			throws BrowseCategoriesServiceException {
		try {
			List<Category> categories = this.queryService
					.findCategoriesInRegion(regionName);
			return categories;
		} catch (QueryServiceException e) {
			throw new BrowseCategoriesServiceException(
					"Error in retrieving all categories in region "
							+ regionName + ".", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Category> getCategoriesByName(String categoryName)
			throws BrowseCategoriesServiceException {
		try {
			List<Category> categories = this.basicQueryService
					.findCategoriesByName(categoryName);
			return categories;
		} catch (QueryServiceException e) {
			throw new BrowseCategoriesServiceException(
					"Error in retrieving categories with name " + categoryName
							+ ".", e);
		}
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public List<Item> getItemsByName(String itemName)
			throws BrowseCategoriesServiceException {
		try {
			List<Item> items = this.basicQueryService.findItemsByName(itemName);
			return items;
		} catch (QueryServiceException e) {
			throw new BrowseCategoriesServiceException(
					"Error in retrieving items with name " + itemName + ".", e);
		}
	}

}
