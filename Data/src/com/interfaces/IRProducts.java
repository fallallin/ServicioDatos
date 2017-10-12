package com.interfaces;

import java.util.List;

import javax.ejb.Remote;

import com.dto.request.RequestProductDetailDTO;
import com.dto.result.ResultProductDetailDTO;

@Remote
public interface IRProducts {

	/**
	 * 
	 * @param requestProductDetail
	 * @return
	 * @author FALLALLIN
	 */
	public List<ResultProductDetailDTO> getProductDetail(RequestProductDetailDTO requestProductDetail) ;
}