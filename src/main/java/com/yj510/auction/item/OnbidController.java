package com.yj510.auction.item;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.StringReader;

@RestController
public class OnbidController {

    @Autowired
    private ItemRepository itemRepository;

    private static final String API_URL = "http://openapi.onbid.co.kr/openapi/services/KamcoPblsalThingInquireSvc/getKamcoPbctCltrList?serviceKey=R90p9POAnp0s%2BoFHji6L%2BevVCe11nTSjJBHHXWEwj6hoDy1pwg%2Bu1vRvrHQiA3Ouz8ccH%2BrdR6%2FKalUjtyfagg%3D%3D&pageNo=1&numOfRows=100";

    @GetMapping("/fetchData")
    public String fetchDataAndSave() {
        try {
            // OpenAPI로부터 데이터 받아오기
            String xmlResponse = fetchDataFromAPI();

            // 받아온 XML 데이터를 파싱하고 DB에 저장
            saveDataToDatabase(xmlResponse);

            return "데이터를 성공적으로 저장했습니다!";
        } catch (Exception e) {
            e.printStackTrace();
            return "데이터 저장 중 오류가 발생했습니다.";
        }
    }

    private String fetchDataFromAPI() {
        RestTemplate restTemplate = new RestTemplate();
        return restTemplate.getForObject(API_URL, String.class);
    }

    private void saveDataToDatabase(String xmlResponse) throws Exception {
        // XML 파싱
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(new InputSource(new StringReader(xmlResponse)));

        NodeList nodeList = doc.getElementsByTagName("item");

        for (int i = 0; i < nodeList.getLength(); i++) {
            Element element = (Element) nodeList.item(i);

            // 데이터를 DB에 저장
            Item item = new Item();
            item.setPublicNoticeNo(getLongValue(element, "PLNM_NO"));
            item.setAuctionNo(getLongValue(element, "PBCT_NO"));
            item.setAuctionConditionNo(getLongValue(element, "PBCT_CDTN_NO"));
            item.setItemNo(getLongValue(element, "CLTR_NO"));
            item.setItemHistoryNo(getLongValue(element, "CLTR_HSTR_NO"));
            item.setScreenGroupCode(getStringValue(element, "SCRN_GRP_CD"));
            item.setCategoryFullName(getStringValue(element, "CTGR_FULL_NM"));
            item.setBidNo(getStringValue(element, "BID_MNMT_NO"));
            item.setItemName(getStringValue(element, "CLTR_NM"));
            item.setItemManagementNo(getStringValue(element, "CLTR_MNMT_NO"));
            item.setLandAddress(getStringValue(element, "LDNM_ADRS"));
            item.setRoadAddress(getStringValue(element, "NMRD_ADRS"));
            item.setLandPnu(getStringValue(element, "LDNM_PNU"));
            item.setDisposalMethodCode(getStringValue(element, "DPSL_MTD_CD"));
            item.setDisposalMethodName(getStringValue(element, "DPSL_MTD_NM"));
            item.setBidMethodName(getStringValue(element, "BID_MTD_NM"));
            item.setMinBidPrice(getLongValue(element, "MIN_BID_PRC"));
            item.setAppraisalAmount(getLongValue(element, "APSL_ASES_AVG_AMT"));
            item.setFeeRate(getStringValue(element, "FEE_RATE"));
            item.setBidStartDateTime(getStringValue(element, "PBCT_BEGN_DTM"));
            item.setBidEndDateTime(getStringValue(element, "PBCT_CLS_DTM"));
            item.setItemStatus(getStringValue(element, "PBCT_CLTR_STAT_NM"));
            item.setFailedBidCount(getIntValue(element, "USCBD_CNT"));
            item.setViewCount(getIntValue(element, "IQRY_CNT"));
            item.setItemDetail(getStringValue(element, "GOODS_NM"));
            item.setManufacturer(getStringValue(element, "MANF"));
            item.setModel(getStringValue(element, "MDL"));
            item.setYearMonth(getStringValue(element, "NRGT"));
            item.setTransmission(getStringValue(element, "GRBX"));
            item.setDisplacement(getStringValue(element, "ENDPC"));
            item.setMileage(getStringValue(element, "VHCL_MLGE"));
            item.setFuelType(getStringValue(element, "FUEL"));
            item.setCorporationName(getStringValue(element, "SCRT_NM"));
            item.setBusinessType(getStringValue(element, "TPBZ"));
            item.setItemTypeName(getStringValue(element, "ITM_NM"));
            item.setMembershipName(getStringValue(element, "MMB_RGT_NM"));
            item.setItemImageUrl(getStringValue(element, "CLTR_IMG_FILE"));

            // DB에 저장
            itemRepository.save(item);
        }
    }

    private Long getLongValue(Element element, String tagName) {
        return Long.parseLong(element.getElementsByTagName(tagName).item(0).getTextContent());
    }

    private String getStringValue(Element element, String tagName) {
        return element.getElementsByTagName(tagName).item(0).getTextContent();
    }

    private int getIntValue(Element element, String tagName) {
        return Integer.parseInt(element.getElementsByTagName(tagName).item(0).getTextContent());
    }


}
