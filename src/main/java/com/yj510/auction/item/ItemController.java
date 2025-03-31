package com.yj510.auction.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {
    private final ItemService itemService;

    @GetMapping("/itemList")
    String productList(Model model){
        List<Item> items = itemService.itemFindAll();
        model.addAttribute("items", items);
        for(Item item:items){System.out.println(item);}
        return "item/itemList";}

    @GetMapping("/itemDetail/{id}")
    public String productDetail(@PathVariable Long id, Model model) {
        // Optional<Item> item = itemRepository.findById(id);
        Optional<Item> item = itemService.itemfindById(id);  // service에서 findById 호출 (ItemService 작성 필요)

        if (item.isPresent()) {
            // 상품이 존재할 경우, 상세 정보를 출력하고 model에 담아서 페이지에 전달
            System.out.println("ID : " + item.get().getId());
            System.out.println("상품 이미지 URL : " + item.get().getItemImageUrl());
            System.out.println("상품명 : " + item.get().getItemName());
            System.out.println("카테고리 : " + item.get().getCategoryFullName());
            System.out.println("최저 입찰가 : " + item.get().getMinBidPrice());
            System.out.println("감정가 : " + item.get().getAppraisalAmount());
            System.out.println("입찰 시작일 : " + item.get().getBidStartDateTime());
            System.out.println("입찰 마감일 : " + item.get().getBidEndDateTime());
            System.out.println("물건 상태 : " + item.get().getItemStatus());
            System.out.println("물건 소재지 : " + item.get().getLandAddress());
            System.out.println("처분 방식 : " + item.get().getDisposalMethodName());
            System.out.println("입찰 방식 : " + item.get().getBidMethodName());
            System.out.println("유찰 횟수 : " + item.get().getFailedBidCount());
            System.out.println("조회수 : " + item.get().getViewCount());
            System.out.println("상세 정보 : " + item.get().getItemDetail());

            // 모델에 상품 상세 정보를 담아서 페이지로 전달
            model.addAttribute("item", item.get());

            // 타임리프로 처리할 페이지로 이동
            return "item/itemDetail";  // productDetail.html 페이지로 이동
        } else {
            // 상품을 찾을 수 없을 경우, 목록 페이지로 리디렉션
            System.out.println("상품 경로 없음");
            return "redirect:/itemList";  // 상품 목록 페이지로 리디렉션
        }
    }
    /*
    @GetMapping("/productRegister")
    String productRegister(){
        return "productRegistration";
    }


    @PostMapping("/itemRegister")
    public String itemRegister(
            BigInteger plnmNo, BigInteger pbctNo, BigInteger pbctCdtmNo, BigInteger cltrNo, BigInteger cltrHstrNo,
            String scrnGrpCd, String ctgrFullNm, String bidMnmtNo, String cltrNm, String cltrMnmtNo,
            String ldnmAdrs, String nmrdAdrs, String ldnmPnu, String dpslMtdCd, String dpslMtdNm,
            String bidMtdNm, BigInteger minBidPrc, BigInteger apslAsesAvgAmt, String feeRate, String pbctBegnDtm,
            String pbctClsDtm, String pbctCltrStatNm, BigInteger usCbdCnt, BigInteger iqryCnt, String goodsNm,
            String manf, String mdl, String nrgt, String grbx, String endpc, String vhclMlge,
            String fuel, String scrtNm, String tpBz, String itmNm, String mmbRgtNm, String cltrImgFile) {

        Item item = new Item();
        item.setPlnmNo(plnmNo);
        item.setPbctNo(pbctNo);
        item.setPbctCdtmNo(pbctCdtmNo);
        item.setCltrNo(cltrNo);
        item.setCltrHstrNo(cltrHstrNo);
        item.setScrnGrpCd(scrnGrpCd);
        item.setCtgrFullNm(ctgrFullNm);
        item.setBidMnmtNo(bidMnmtNo);
        item.setCltrNm(cltrNm);
        item.setCltrMnmtNo(cltrMnmtNo);
        item.setLdnmAdrs(ldnmAdrs);
        item.setNmrdAdrs(nmrdAdrs);
        item.setLdnmPnu(ldnmPnu);
        item.setDpslMtdCd(dpslMtdCd);
        item.setDpslMtdNm(dpslMtdNm);
        item.setBidMtdNm(bidMtdNm);
        item.setMinBidPrc(minBidPrc);
        item.setApslAsesAvgAmt(apslAsesAvgAmt);
        item.setFeeRate(feeRate);
        item.setPbctBegnDtm(pbctBegnDtm);
        item.setPbctClsDtm(pbctClsDtm);
        item.setPbctCltrStatNm(pbctCltrStatNm);
        item.setUsCbdCnt(usCbdCnt);
        item.setIqryCnt(iqryCnt);
        item.setGoodsNm(goodsNm);
        item.setManf(manf);
        item.setMdl(mdl);
        item.setNrgt(nrgt);
        item.setGrbx(grbx);
        item.setEndpc(endpc);
        item.setVhclMlge(vhclMlge);
        item.setFuel(fuel);
        item.setScrtNm(scrtNm);
        item.setTpBz(tpBz);
        item.setItmNm(itmNm);
        item.setMmbRgtNm(mmbRgtNm);
        item.setCltrImgFile(cltrImgFile);

        itemService.itemSave(item);
        return "redirect:/itemList";
    }

    @GetMapping("/productDetail/{id}")
    String productDetail(@PathVariable Long id, Model model){
        //Optional<Product> product = productRepository.findById(id);
        Optional<Item> item = itemService.itemfindById(id);
        if(item.isPresent()){
            model.addAttribute("item",item.get());
            return "productDetail";
        }else {
            System.out.println("상품 경로 없음");
            return "redirect:/productList";
        }
    }

    @GetMapping("/productEdit/{id}")
    String productEdit(@PathVariable Long id, Model model){
        //Optional<Product> product = productRepository.findById(id);
        Optional<Item> item = itemService.itemfindById(id);
        if(item.isPresent()){
            model.addAttribute("item",item.get());
            return "productEdit";
        }else {
            System.out.println("상품 경로 없음");
            return "redirect:/productList";
        }
    }

    @PostMapping("/productEdit")
    String productEdit(@ModelAttribute Item item){
        //productRepository.save(product);
        itemService.itemSave(item);
        return "redirect:/productList";
    }

    String deleteProduct(@ModelAttribute Item item){
        itemService.itemDeleteById(item.getId());
        return "redirect:/productList";
    }*/

}
