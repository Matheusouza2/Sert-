package com.sert.controler;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

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

	public DeserializableNfe() {
		this.nFeEntrada = new NFeEntrada();
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

			// Insere as informações do Dest. e do Emit. na entidade
			nFeEntrada.setCnpjDest(Long.parseLong(dest.getCNPJ()));
			nFeEntrada.setChave(chavNfe);
			nFeEntrada.setNumNota(nNfe);
			nFeEntrada.setCnpjForn(Long.parseLong(emit.getCNPJ()));
			nFeEntrada.setIeForn(Long.parseLong(emit.getIE()));
			nFeEntrada.setNomeFant(emit.getXFant());
			nFeEntrada.setRazSocial(emit.getXNome());
			nFeEntrada.setLograForn(emit.getEnderEmit().getXLgr());
			nFeEntrada.setNumrEndForn(emit.getEnderEmit().getNro());
			nFeEntrada.setBairroForn(emit.getEnderEmit().getXBairro());
			nFeEntrada.setCidadeForn(emit.getEnderEmit().getXMun());
			nFeEntrada.setUfForn(String.valueOf(emit.getEnderEmit().getUF()));
			nFeEntrada.setFoneForn(Long.parseLong(emit.getEnderEmit().getFone()));

			// Iterando na lista de produtos da NFe
			for (TNFe.InfNFe.Det item : tNfeProc.getNFe().getInfNFe().getDet()) {
				mercadoria = new MercadoriaNFe(null, 0, null, null, 0, 0, 0, 0);
				mercadoria.setCodProd(item.getProd().getCProd());
				if(!item.getProd().getCEANTrib().equals("SEM GTIN")){
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
			nFeEntrada.setMercadorias(mercadorias);
			return nFeEntrada;
		} catch (JAXBException e) {
			e.printStackTrace();
		}
		return nFeEntrada;
	}
}