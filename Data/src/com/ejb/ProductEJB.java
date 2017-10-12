package com.ejb;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.jboss.logging.Logger;

import com.dto.request.RequestProductDetailDTO;
import com.dto.result.ResultProductDetailDTO;
import com.interfaces.ILProducts;
import com.interfaces.IRProducts;
import com.qualifier.Resource;

/**
 * Session Bean implementation class ImposicionISEJB
 */
@Stateless(name = "ProductEJB")
@LocalBean
public class ProductEJB implements ILProducts, IRProducts {

	private final static Logger logger = Logger.getLogger(ProductEJB.class.getName());

	private EntityManager em;

	@Override
	public List<ResultProductDetailDTO> getProductDetail(RequestProductDetailDTO requestProductDetail) {
		logger.debug("ProductEJB::consutarMultas(SolicitudConsultaMultasDTO)");
		em = Resource.getEntityManager();
		em.getTransaction().begin();

		List<ResultProductDetailDTO> listRpdDTO = new ArrayList<ResultProductDetailDTO>();
		try {

			Map<String, Object> param = new HashMap<String, Object>();

			StringBuilder query = new StringBuilder();
			query.append("SELECT pr.name AS name,");
			query.append(" pr.description AS description,");
			query.append(" pr.price AS price,");
			query.append(" pr.externalidentifier AS externalidentifier,");
			query.append(" pd.producer AS producer,");
			query.append(" cat.category AS category,");
			query.append(" im.finalpath AS image");
			query.append(" FROM products pr");
			query.append(" INNER JOIN images im ON im.idproduct = pr.id");
			query.append(" INNER JOIN producers pd ON pr.idproducer = pd.id");
			query.append(" INNER JOIN productscategories pcat ON pcat.idproduct = pr.id");
			query.append(" INNER JOIN categories cat ON cat.id = pcat.idproduct");
			query.append(" WHERE 1 = 1");

			if (requestProductDetail.getIdProduct() != null) {
				query.append("AND pr.id = :idProduct ");
				param.put("idProduct", requestProductDetail.getIdProduct());
			}

			System.out.println("Superquery: " + query.toString());

			Query querySQL = em.createNativeQuery(query.toString());

			for (Entry<String, Object> parameter : param.entrySet()) {
				querySQL.setParameter(parameter.getKey(), parameter.getValue());
			}

			@SuppressWarnings({ "unchecked" })
			List<Object[]> productList = querySQL.getResultList();

			ResultProductDetailDTO rpdDTO = null;

			if (productList != null && !productList.isEmpty()) {

				for (Object[] obligacion : productList) {

					// Arma obligacion que va a enviar al proceso
					rpdDTO = new ResultProductDetailDTO();
					if (obligacion[0] != null) {
						rpdDTO.setName((String) obligacion[0]);
					}

					if (obligacion[1] != null) {
						rpdDTO.setDescription((String) obligacion[1]);
					}

					if (obligacion[2] != null) {
						rpdDTO.setPrice((String) obligacion[2]);
					}
					if (obligacion[3] != null) {
						rpdDTO.setExternalidentifier((String) obligacion[3]);
					}
					if (obligacion[4] != null) {
						rpdDTO.setProducer((String) obligacion[4]);
					}
					if (obligacion[5] != null) {
						rpdDTO.setCategory((String) obligacion[5]);
					}
					if (obligacion[6] != null) {
						rpdDTO.setImage((String) obligacion[6]);
					}

					listRpdDTO.add(rpdDTO);
				}
			}
		} catch (Exception e) {
			logger.debug("Totio por:" + e.getMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}
		return listRpdDTO;
	}

}
