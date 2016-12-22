/**
 * 
 */
package com.ss.task.dao.shop.details.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ss.task.shop.details.request.ShopDetails;
import com.ss.task.shop.details.request.vo.ShopDetailsVo;
import com.ss.task.shop.details.service.ShopDetailsService;

/**
 * @author Saurav Singh
 *
 */
@RestController
@RequestMapping("/shopdetails")
public class ShopDetailsController {

	private static final Logger LOGGER = LoggerFactory.getLogger(ShopDetailsController.class);

	@Autowired
	@Qualifier("shopDetailsService")
	private ShopDetailsService shopDetailsService;

	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> saveShopDetails(@RequestBody ShopDetails shopDetails) {
		LOGGER.debug("Started endpoint method {}, params - {}", "saveShopDetails", shopDetails.toString());
		try {
			int saveCount = shopDetailsService.saveShopDetails(shopDetails);
			if (saveCount == 0) {
				return new ResponseEntity<String>("Unable to find latitude and logitude of requested shop location, please check and resubmit again",
						HttpStatus.BAD_REQUEST);
			}
		} catch (Exception e) {
			LOGGER.error("Exception {}", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			LOGGER.debug("Finished endpoint method {}", "saveShopDetails");
		}
		return new ResponseEntity<String>("Shop address added!", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<?> getNearByShops(@RequestParam(required = true, name = "customerLatitude") String customerLatitude,
			@RequestParam(required = true, name = "customerLongitude") String customerLongitude) {
		LOGGER.debug("Started endpoint method {}, params - {}", "getNearByShops", new Object[] { customerLatitude, customerLongitude });
		List<ShopDetailsVo> output = null;
		try {
			output = shopDetailsService.findShopNearByLatLng(customerLatitude, customerLongitude);
		} catch (Exception e) {
			LOGGER.error("Exception {}", e);
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		} finally {
			LOGGER.debug("Finished endpoint method {}", "saveShopDetails");
		}
		return new ResponseEntity<>(output, HttpStatus.OK);
	}
}
