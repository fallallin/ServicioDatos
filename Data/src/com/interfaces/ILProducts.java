package com.interfaces;

import java.util.List;

import javax.ejb.Local;

import com.dto.request.RequestProductDetailDTO;
import com.dto.result.ResultProductDetailDTO;

@Local
public interface ILProducts {
	
	/**
	 * 
	 * @param requestProductDetail
	 * @return
	 * @author FALLALLIN
	 */
	public List<ResultProductDetailDTO> getProductDetail(RequestProductDetailDTO requestProductDetail) ;
}
