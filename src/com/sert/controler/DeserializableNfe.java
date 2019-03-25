package com.sert.controler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.sert.entidades.Fornecedor;
import com.sert.entidades.MercadoriaNFe;
import com.sert.entidades.NFeEntrada;
import com.sert.xmlcontroler.TNFe;
import com.sert.xmlcontroler.TNFe.InfNFe.Dest;
import com.sert.xmlcontroler.TNFe.InfNFe.Emit;
import com.sert.xmlcontroler.TNfeProc;

/*
*	Desenvolvido por Fabiano Costa de Alvarenga.
*	Esta classe recupera dados de um XML de NF-e.
*
*/
public class DeserializableNfe {
	private String xmlFilePathNFe;
	private JAXBContext context = null;
	private TNfeProc tNfeProc = null;
	private NFeEntrada nFeEntrada;
	private List<MercadoriaNFe> mercadorias;
	private MercadoriaNFe mercadoria;
	private Fornecedor fornecedor;

	public DeserializableNfe() {
		this.nFeEntrada = new NFeEntrada();
		this.fornecedor = new Fornecedor();
		mercadorias = new ArrayList<>();
	}

	public NFeEntrada lerXml(String caminho) throws SQLException, ClassNotFoundException, IOException {
		try {
			this.xmlFilePathNFe = caminho;
			// Realizando o parser do XML da NFe para Objeto Java
			context = JAXBContext.newInstance(TNfeProc.class.getPackage().getName());
			Unmarshaller unmarshaller1 = context.createUnmarshaller();

			// Este é o seu Objeto Java da NFe (tNfeProc)
			tNfeProc = (TNfeProc) unmarshaller1.unmarshal(new File(xmlFilePathNFe));

			Emit emit = tNfeProc.getNFe().getInfNFe().getEmit();
			Dest dest = tNfeProc.getNFe().getInfNFe().getDest();
			String chavNfe = tNfeProc.getNFe().getInfNFe().getId().replace("NFe", "").replace("\"", "");
			int nNfe = Integer.parseInt(tNfeProc.getNFe().getInfNFe().getIde().getNNF());

			// Insere as informações do Dest., numero e chave da nota
			nFeEntrada.setCnpjDest(Long.parseLong(dest.getCNPJ()));
			nFeEntrada.setChave(chavNfe);
			nFeEntrada.setNumNota(nNfe);
			nFeEntrada.setValNota(Float.parseFloat(tNfeProc.getNFe().getInfNFe().getTotal().getICMSTot().getVNF()));

			// insere as informações do emitente
			fornecedor.setCnpjForn(emit.getCNPJ());
			fornecedor.setIeForn(emit.getIE());
			fornecedor.setNomeFant(emit.getXFant());
			fornecedor.setRazSocial(emit.getXNome());
			fornecedor.setLograForn(emit.getEnderEmit().getXLgr());
			fornecedor.setNumrEndForn(Integer.parseInt(emit.getEnderEmit().getNro()));
			fornecedor.setBairroForn(emit.getEnderEmit().getXBairro());
			fornecedor.setCidadeForn(emit.getEnderEmit().getXMun());
			fornecedor.setUfForn(String.valueOf(emit.getEnderEmit().getUF()));
			fornecedor.setFoneForn(Long.parseLong(emit.getEnderEmit().getFone()));
			nFeEntrada.setFornecedor(fornecedor);

			// Iterando na lista de produtos da NFe
			for (TNFe.InfNFe.Det item : tNfeProc.getNFe().getInfNFe().getDet()) {
				mercadoria = new MercadoriaNFe();
				mercadoria.setCodProd(Long.parseLong(item.getProd().getCProd()));
				if (!item.getProd().getCEANTrib().equals("SEM GTIN") && !item.getProd().getCEANTrib().equals("")) {
					mercadoria.setCodBarras(Long.parseLong(item.getProd().getCEANTrib()));
				}
				mercadoria.setMercadoria(item.getProd().getXProd());
				mercadoria.setUnd(item.getProd().getUCom());
				mercadoria.setQuantCompra(Float.parseFloat(item.getProd().getQCom()));
				mercadoria.setPrecoUn(Float.parseFloat(item.getProd().getVUnCom()));
				mercadoria.setPrecoTotal(Float.parseFloat(item.getProd().getVProd()));
				if (item.getProd().getVDesc() != null) {
					mercadoria.setValDesc(Float.parseFloat(item.getProd().getVDesc()));
				}

				mercadorias.add(mercadoria);
			}

//			for (int i = 0; i < mercadorias.size(); i++) {
//				for (int j = 0; j < mercadorias.size(); i++) {
//					if (mercadorias.get(i).getCodBarras() == mercadorias.get(j).getCodBarras()
//							&& mercadorias.get(i).getMercadoria().equals(mercadorias.get(j).getMercadoria())) {
//
//					}
//				}
//			}

			nFeEntrada.setMercadorias(mercadorias);
			return nFeEntrada;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return nFeEntrada;
	}
}