package com.yj510.auction.item;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

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
