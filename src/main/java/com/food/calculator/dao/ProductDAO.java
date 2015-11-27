package com.food.calculator.dao;

import javax.inject.Singleton;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.food.calculator.cache.model.Product;
import com.food.calculator.services.modules.EntityManagerProvider;

@Singleton
public class ProductDAO {

	private static final String THE_PRODUCT_IS_NULL = "The product must not be null";
	private static final String NDBNO_IS_NULL = "Id must not be null";
	private static final String NDBNO = "ndbno";
	private static final String PRODUCT_FIND_BYNDBNO = "Product.findByndbno";
	private static final Logger LOGGER = LoggerFactory.getLogger(ProductDAO.class);

	/**
	 * Add product in database.
	 * 
	 * @param product
	 */
	public void add(Product product) {

		if (product == null) {
			LOGGER.error(THE_PRODUCT_IS_NULL);
			return;
		}

		EntityManager em = new EntityManagerProvider().getEntityManager();
		em.getTransaction().begin();
		em.persist(product);
		em.getTransaction().commit();
		em.close();
	}

	/**
	 * Search product with ndbno(id)
	 * 
	 * @param ndbno
	 * @return
	 */
	public Product findById(Long ndbno) {

		if (ndbno == null) {
			LOGGER.error(NDBNO_IS_NULL);
			return null;
		}

		Product product = null;
		EntityManager em = new EntityManagerProvider().getEntityManager();
		Query q = em.createNamedQuery(PRODUCT_FIND_BYNDBNO).setParameter(NDBNO, ndbno);
		if (q.getResultList().size() != 0) {
			product = (Product) q.getSingleResult();
		}
		em.close();
		return product;
	}

	/**
	 * Remove product from database.
	 * 
	 * @param currentProduct
	 */
	public void remove(Product product) {

		if (product == null) {
			LOGGER.error(THE_PRODUCT_IS_NULL);
			return;
		}
		EntityManager em = new EntityManagerProvider().getEntityManager();
		em.getTransaction().begin();
		em.remove(product);
		em.getTransaction().commit();
		em.close();
	}
}
