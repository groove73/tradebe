package com.trade.securities.adapter.`in`.grpc

import com.trade.securities.application.port.`in`.*
import com.trade.securities.grpc.*
import io.quarkus.grpc.GrpcService
import io.smallrye.mutiny.Uni
import jakarta.inject.Inject

// Import domain models with aliases to prevent conflict with proto generated message class names
import com.trade.securities.domain.BondTradingInfo as DomainBondTradingInfo
import com.trade.securities.domain.CommodityTradingInfo as DomainCommodityTradingInfo
import com.trade.securities.domain.DerivativesTradingInfo as DomainDerivativesTradingInfo
import com.trade.securities.domain.ElwTradingInfo as DomainElwTradingInfo
import com.trade.securities.domain.EtfTradingInfo as DomainEtfTradingInfo
import com.trade.securities.domain.EtnTradingInfo as DomainEtnTradingInfo
import com.trade.securities.domain.FscBeneficiaryCertificate as DomainFscBeneficiaryCertificate
import com.trade.securities.domain.FscNewShareCertificate as DomainFscNewShareCertificate
import com.trade.securities.domain.FscStockPrice as DomainFscStockPrice
import com.trade.securities.domain.FscStockSubscriptionRight as DomainFscStockSubscriptionRight
import com.trade.securities.domain.MarketData as DomainMarketData
import com.trade.securities.domain.StockData as DomainStockData

@GrpcService
class SecuritiesGrpcService(
    private val getMarketDataUseCase: GetMarketDataUseCase,
    private val loadStockDataUseCase: LoadStockDataUseCase,
    private val getBondTradingInfoUseCase: GetBondTradingInfoUseCase,
    private val getCommodityTradingInfoUseCase: GetCommodityTradingInfoUseCase,
    private val getDerivativesTradingInfoUseCase: GetDerivativesTradingInfoUseCase,
    private val getElwTradingInfoUseCase: GetElwTradingInfoUseCase,
    private val getEtfTradingInfoUseCase: GetEtfTradingInfoUseCase,
    private val getEtnTradingInfoUseCase: GetEtnTradingInfoUseCase,
    private val getFscStockPriceUseCase: GetFscStockPriceUseCase,
    private val getFscNewShareCertificateUseCase: GetFscNewShareCertificateUseCase,
    private val getFscBeneficiaryCertificateUseCase: GetFscBeneficiaryCertificateUseCase,
    private val getFscStockSubscriptionRightUseCase: GetFscStockSubscriptionRightUseCase
) : SecuritiesService {

    override fun getMarketData(request: MarketDataRequest): Uni<MarketDataList> {
        val domainItems = getMarketDataUseCase.getMarketData(request.date, request.type)
        val protoItems = domainItems.map {
            MarketData.newBuilder()
                .setBasDt(it.basDt ?: "")
                .setIdxNm(it.idxNm ?: "")
                .setClpr(it.clpr ?: "")
                .setFltRt(it.fltRt ?: "")
                .build()
        }
        val response = MarketDataList.newBuilder().addAllItems(protoItems).build()
        return Uni.createFrom().item(response)
    }

    override fun getStockData(request: StockDataRequest): Uni<StockDataList> {
        val domainItems = loadStockDataUseCase.getStockData(request.date, request.type)
        val protoItems = domainItems.map {
            StockData.newBuilder()
                .setBasDd(it.basDd ?: "")
                .setIsuCd(it.isuCd ?: "")
                .setIsuNm(it.isuNm ?: "")
                .setMktNm(it.mktNm ?: "")
                .setSectTpNm(it.sectTpNm ?: "")
                .setTddClsprc(it.tddClsprc ?: "")
                .setCmpprevddPrc(it.cmpprevddPrc ?: "")
                .setFlucRt(it.flucRt ?: "")
                .setTddOpnprc(it.tddOpnprc ?: "")
                .setTddHgprc(it.tddHgprc ?: "")
                .setTddLwprc(it.tddLwprc ?: "")
                .setAccTrdvol(it.accTrdvol ?: "")
                .setAccTrdval(it.accTrdval ?: "")
                .setMktcap(it.mktcap ?: "")
                .setListShrs(it.listShrs ?: "")
                .build()
        }
        val response = StockDataList.newBuilder().addAllItems(protoItems).build()
        return Uni.createFrom().item(response)
    }

    override fun getBondTradingInfo(request: BondTradingInfoRequest): Uni<BondTradingInfoList> {
        val domainItems = getBondTradingInfoUseCase.getBondTradingInfo(request.marketType, request.basDd)
        val protoItems = domainItems.map {
            BondTradingInfo.newBuilder()
                .setBasDd(it.basDd ?: "")
                .setMktNm(it.mktNm ?: "")
                .setIsuCd(it.isuCd ?: "")
                .setIsuNm(it.isuNm ?: "")
                .setClsprc(it.clsprc ?: "")
                .setCmpprevddPrc(it.cmpprevddPrc ?: "")
                .setClsprcYd(it.clsprcYd ?: "")
                .setOpnprc(it.opnprc ?: "")
                .setOpnprcYd(it.opnprcYd ?: "")
                .setHgprc(it.hgprc ?: "")
                .setHgprcYd(it.hgprcYd ?: "")
                .setLwprc(it.lwprc ?: "")
                .setLwprcYd(it.lwprcYd ?: "")
                .setAccTrdvol(it.accTrdvol ?: "")
                .setAccTrdval(it.accTrdval ?: "")
                .setMktcap(it.mktcap ?: "")
                .setListShrs(it.listShrs ?: "")
                .setBndExpTpNm(it.bndExpTpNm ?: "")
                .setGovbndIsuTpNm(it.govbndIsuTpNm ?: "")
                .build()
        }
        val response = BondTradingInfoList.newBuilder().addAllItems(protoItems).build()
        return Uni.createFrom().item(response)
    }

    override fun getCommodityTradingInfo(request: CommodityTradingInfoRequest): Uni<CommodityTradingInfoList> {
        val domainItems = getCommodityTradingInfoUseCase.getCommodityTradingInfo(request.marketType, request.basDd)
        val protoItems = domainItems.map {
            CommodityTradingInfo.newBuilder()
                .setBasDd(it.basDd ?: "")
                .setIsuCd(it.isuCd ?: "")
                .setIsuNm(it.isuNm ?: "")
                .setAccTrdvol(it.accTrdvol ?: "")
                .setAccTrdval(it.accTrdval ?: "")
                .setTddClsprc(it.tddClsprc ?: "")
                .setCmpprevddPrc(it.cmpprevddPrc ?: "")
                .setFlucRt(it.flucRt ?: "")
                .setTddOpnprc(it.tddOpnprc ?: "")
                .setTddHgprc(it.tddHgprc ?: "")
                .setTddLwprc(it.tddLwprc ?: "")
                .setOilNm(it.oilNm ?: "")
                .setWtAvgPrc(it.wtAvgPrc ?: "")
                .setWtDisAvgPrc(it.wtDisAvgPrc ?: "")
                .build()
        }
        val response = CommodityTradingInfoList.newBuilder().addAllItems(protoItems).build()
        return Uni.createFrom().item(response)
    }

    override fun getDerivativesTradingInfo(request: DerivativesTradingInfoRequest): Uni<DerivativesTradingInfoList> {
        val domainItems = getDerivativesTradingInfoUseCase.getDerivativesTradingInfo(request.marketType, request.basDd)
        val protoItems = domainItems.map {
            DerivativesTradingInfo.newBuilder()
                .setBasDd(it.basDd ?: "")
                .setProdNm(it.prodNm ?: "")
                .setMktNm(it.mktNm ?: "")
                .setIsuCd(it.isuCd ?: "")
                .setIsuNm(it.isuNm ?: "")
                .setTddClsprc(it.tddClsprc ?: "")
                .setCmpprevddPrc(it.cmpprevddPrc ?: "")
                .setTddOpnprc(it.tddOpnprc ?: "")
                .setTddHgprc(it.tddHgprc ?: "")
                .setTddLwprc(it.tddLwprc ?: "")
                .setAccTrdvol(it.accTrdvol ?: "")
                .setAccTrdval(it.accTrdval ?: "")
                .setAccOpnintQty(it.accOpnintQty ?: "")
                .setSpotPrc(it.spotPrc ?: "")
                .setSetlPrc(it.setlPrc ?: "")
                .setRghtTpNm(it.rghtTpNm ?: "")
                .setImpVolt(it.impVolt ?: "")
                .setNxtdBasPrc(it.nxtdBasPrc ?: "")
                .build()
        }
        val response = DerivativesTradingInfoList.newBuilder().addAllItems(protoItems).build()
        return Uni.createFrom().item(response)
    }

    override fun getElwTradingInfo(request: ElwTradingInfoRequest): Uni<ElwTradingInfoList> {
        val domainItems = getElwTradingInfoUseCase.getElwTradingInfo(request.basDd)
        val protoItems = domainItems.map {
            ElwTradingInfo.newBuilder()
                .setBasDd(it.basDd ?: "")
                .setIsuCd(it.isuCd ?: "")
                .setIsuNm(it.isuNm ?: "")
                .setTddClsprc(it.tddClsprc ?: "")
                .setCmpprevddPrc(it.cmpprevddPrc ?: "")
                .setTddOpnprc(it.tddOpnprc ?: "")
                .setTddHgprc(it.tddHgprc ?: "")
                .setTddLwprc(it.tddLwprc ?: "")
                .setAccTrdvol(it.accTrdvol ?: "")
                .setAccTrdval(it.accTrdval ?: "")
                .setMktcap(it.mktcap ?: "")
                .setListShrs(it.listShrs ?: "")
                .setUlyNm(it.ulyNm ?: "")
                .setUlyPrc(it.ulyPrc ?: "")
                .setCmpprevddPrcUly(it.cmpprevddPrcUly ?: "")
                .setFlucRtUly(it.flucRtUly ?: "")
                .build()
        }
        val response = ElwTradingInfoList.newBuilder().addAllItems(protoItems).build()
        return Uni.createFrom().item(response)
    }

    override fun getEtfTradingInfo(request: EtfTradingInfoRequest): Uni<EtfTradingInfoList> {
        val domainItems = getEtfTradingInfoUseCase.getEtfTradingInfo(request.basDd)
        val protoItems = domainItems.map {
            EtfTradingInfo.newBuilder()
                .setBasDd(it.basDd ?: "")
                .setIsuCd(it.isuCd ?: "")
                .setIsuNm(it.isuNm ?: "")
                .setTddClsprc(it.tddClsprc ?: "")
                .setCmpprevddPrc(it.cmpprevddPrc ?: "")
                .setFlucRt(it.flucRt ?: "")
                .setNav(it.nav ?: "")
                .setTddOpnprc(it.tddOpnprc ?: "")
                .setTddHgprc(it.tddHgprc ?: "")
                .setTddLwprc(it.tddLwprc ?: "")
                .setAccTrdvol(it.accTrdvol ?: "")
                .setAccTrdval(it.accTrdval ?: "")
                .setMktcap(it.mktcap ?: "")
                .setInvstasstNetasstTotamt(it.invstasstNetasstTotamt ?: "")
                .setListShrs(it.listShrs ?: "")
                .setIdxIndNm(it.idxIndNm ?: "")
                .setObjStkprcIdx(it.objStkprcIdx ?: "")
                .setCmpprevddIdx(it.cmpprevddIdx ?: "")
                .setFlucRtIdx(it.flucRtIdx ?: "")
                .build()
        }
        val response = EtfTradingInfoList.newBuilder().addAllItems(protoItems).build()
        return Uni.createFrom().item(response)
    }

    override fun getEtnTradingInfo(request: EtnTradingInfoRequest): Uni<EtnTradingInfoList> {
        val domainItems = getEtnTradingInfoUseCase.getEtnTradingInfo(request.basDd)
        val protoItems = domainItems.map {
            EtnTradingInfo.newBuilder()
                .setBasDd(it.basDd ?: "")
                .setIsuCd(it.isuCd ?: "")
                .setIsuNm(it.isuNm ?: "")
                .setTddClsprc(it.tddClsprc ?: "")
                .setCmpprevddPrc(it.cmpprevddPrc ?: "")
                .setFlucRt(it.flucRt ?: "")
                .setPer1SecuIndicVal(it.per1secuIndicVal ?: "")
                .setTddOpnprc(it.tddOpnprc ?: "")
                .setTddHgprc(it.tddHgprc ?: "")
                .setTddLwprc(it.tddLwprc ?: "")
                .setAccTrdvol(it.accTrdvol ?: "")
                .setAccTrdval(it.accTrdval ?: "")
                .setMktcap(it.mktcap ?: "")
                .setIndicValAmt(it.indicValAmt ?: "")
                .setListShrs(it.listShrs ?: "")
                .setIdxIndNm(it.idxIndNm ?: "")
                .setObjStkprcIdx(it.objStkprcIdx ?: "")
                .setCmpprevddIdx(it.cmpprevddIdx ?: "")
                .setFlucRtIdx(it.flucRtIdx ?: "")
                .build()
        }
        val response = EtnTradingInfoList.newBuilder().addAllItems(protoItems).build()
        return Uni.createFrom().item(response)
    }

    @Suppress("UNCHECKED_CAST")
    override fun getStockPrices(request: FscQuotationRequest): Uni<FscStockPriceResponse> {
        val result = getFscStockPriceUseCase.getStockPrices(
            request.pageNo, request.numOfRows, request.basDt, 
            request.itmsNm.takeIf { it.isNotEmpty() }, 
            request.likeItmsNm.takeIf { it.isNotEmpty() }
        )
        val domainItems = result["items"] as? List<DomainFscStockPrice> ?: emptyList()
        val protoItems = domainItems.map {
            FscStockPrice.newBuilder()
                .setBasDt(it.basDt ?: "")
                .setSrtCd(it.srtCd ?: "")
                .setIsinCd(it.isinCd ?: "")
                .setItmsNm(it.itmsNm ?: "")
                .setMrktCtg(it.mrktCtg ?: "")
                .setClpr(it.clpr ?: "")
                .setVs(it.vs ?: "")
                .setFltRt(it.fltRt ?: "")
                .setMkp(it.mkp ?: "")
                .setHipr(it.hipr ?: "")
                .setLopr(it.lopr ?: "")
                .setTrqu(it.trqu ?: "")
                .setTrPrc(it.trPrc ?: "")
                .setLstgStCnt(it.lstgStCnt ?: "")
                .setMrktTotAmt(it.mrktTotAmt ?: "")
                .build()
        }
        val response = FscStockPriceResponse.newBuilder()
            .addAllItems(protoItems)
            .setTotalCount(result["totalCount"] as? Int ?: 0)
            .setPageNo(result["pageNo"] as? Int ?: request.pageNo)
            .setNumOfRows(result["numOfRows"] as? Int ?: request.numOfRows)
            .build()
        return Uni.createFrom().item(response)
    }

    @Suppress("UNCHECKED_CAST")
    override fun getNewShareCertificates(request: FscQuotationRequest): Uni<FscNewShareResponse> {
        val result = getFscNewShareCertificateUseCase.getNewShareCertificates(
            request.pageNo, request.numOfRows, request.basDt, 
            request.itmsNm.takeIf { it.isNotEmpty() }, 
            request.likeItmsNm.takeIf { it.isNotEmpty() }
        )
        val domainItems = result["items"] as? List<DomainFscNewShareCertificate> ?: emptyList()
        val protoItems = domainItems.map {
            FscNewShareCertificate.newBuilder()
                .setBasDt(it.basDt ?: "")
                .setSrtCd(it.srtCd ?: "")
                .setIsinCd(it.isinCd ?: "")
                .setItmsNm(it.itmsNm ?: "")
                .setMrktCtg(it.mrktCtg ?: "")
                .setClpr(it.clpr ?: "")
                .setVs(it.vs ?: "")
                .setFltRt(it.fltRt ?: "")
                .setMkp(it.mkp ?: "")
                .setHipr(it.hipr ?: "")
                .setLopr(it.lopr ?: "")
                .setTrqu(it.trqu ?: "")
                .setTrPrc(it.trPrc ?: "")
                .setLstDt(it.lstDt ?: "")
                .setDelstDt(it.delstDt ?: "")
                .setStckSrtnCd(it.stckSrtnCd ?: "")
                .setStckItmsNm(it.stckItmsNm ?: "")
                .setStckClpr(it.stckClpr ?: "")
                .build()
        }
        val response = FscNewShareResponse.newBuilder()
            .addAllItems(protoItems)
            .setTotalCount(result["totalCount"] as? Int ?: 0)
            .setPageNo(result["pageNo"] as? Int ?: request.pageNo)
            .setNumOfRows(result["numOfRows"] as? Int ?: request.numOfRows)
            .build()
        return Uni.createFrom().item(response)
    }

    @Suppress("UNCHECKED_CAST")
    override fun getBeneficiaryCertificates(request: FscQuotationRequest): Uni<FscBeneficiaryResponse> {
        val result = getFscBeneficiaryCertificateUseCase.getBeneficiaryCertificates(
            request.pageNo, request.numOfRows, request.basDt, 
            request.itmsNm.takeIf { it.isNotEmpty() }, 
            request.likeItmsNm.takeIf { it.isNotEmpty() }
        )
        val domainItems = result["items"] as? List<DomainFscBeneficiaryCertificate> ?: emptyList()
        val protoItems = domainItems.map {
            FscBeneficiaryCertificate.newBuilder()
                .setBasDt(it.basDt ?: "")
                .setSrtCd(it.srtCd ?: "")
                .setIsinCd(it.isinCd ?: "")
                .setItmsNm(it.itmsNm ?: "")
                .setClpr(it.clpr ?: "")
                .setVs(it.vs ?: "")
                .setFltRt(it.fltRt ?: "")
                .setMkp(it.mkp ?: "")
                .setHipr(it.hipr ?: "")
                .setLopr(it.lopr ?: "")
                .setTrqu(it.trqu ?: "")
                .setTrPrc(it.trPrc ?: "")
                .setLstPnt(it.lstPnt ?: "")
                .setMrktTotAmt(it.mrktTotAmt ?: "")
                .build()
        }
        val response = FscBeneficiaryResponse.newBuilder()
            .addAllItems(protoItems)
            .setTotalCount(result["totalCount"] as? Int ?: 0)
            .setPageNo(result["pageNo"] as? Int ?: request.pageNo)
            .setNumOfRows(result["numOfRows"] as? Int ?: request.numOfRows)
            .build()
        return Uni.createFrom().item(response)
    }

    @Suppress("UNCHECKED_CAST")
    override fun getSubscriptionRights(request: FscQuotationRequest): Uni<FscSubscriptionResponse> {
        val result = getFscStockSubscriptionRightUseCase.getSubscriptionRights(
            request.pageNo, request.numOfRows, request.basDt, 
            request.itmsNm.takeIf { it.isNotEmpty() }, 
            request.likeItmsNm.takeIf { it.isNotEmpty() }
        )
        val domainItems = result["items"] as? List<DomainFscStockSubscriptionRight> ?: emptyList()
        val protoItems = domainItems.map {
            FscStockSubscriptionRight.newBuilder()
                .setBasDt(it.basDt ?: "")
                .setSrtCd(it.srtCd ?: "")
                .setIsinCd(it.isinCd ?: "")
                .setItmsNm(it.itmsNm ?: "")
                .setMrktCtg(it.mrktCtg ?: "")
                .setClpr(it.clpr ?: "")
                .setVs(it.vs ?: "")
                .setFltRt(it.fltRt ?: "")
                .setMkp(it.mkp ?: "")
                .setHipr(it.hipr ?: "")
                .setLopr(it.lopr ?: "")
                .setTrqu(it.trqu ?: "")
                .setTrPrc(it.trPrc ?: "")
                .setLstDt(it.lstDt ?: "")
                .setLstPnt(it.lstPnt ?: "")
                .setHngpStrtDt(it.hngpStrtDt ?: "")
                .setHngpEndDt(it.hngpEndDt ?: "")
                .setStckSrtnCd(it.stckSrtnCd ?: "")
                .setStckItmsNm(it.stckItmsNm ?: "")
                .setStckClpr(it.stckClpr ?: "")
                .build()
        }
        val response = FscSubscriptionResponse.newBuilder()
            .addAllItems(protoItems)
            .setTotalCount(result["totalCount"] as? Int ?: 0)
            .setPageNo(result["pageNo"] as? Int ?: request.pageNo)
            .setNumOfRows(result["numOfRows"] as? Int ?: request.numOfRows)
            .build()
        return Uni.createFrom().item(response)
    }
}
