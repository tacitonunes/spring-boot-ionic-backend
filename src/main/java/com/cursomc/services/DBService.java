package com.cursomc.services;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.cursomc.domain.Categoria;
import com.cursomc.domain.Cidade;
import com.cursomc.domain.Cliente;
import com.cursomc.domain.Endereco;
import com.cursomc.domain.Estado;
import com.cursomc.domain.ItemPedido;
import com.cursomc.domain.Pagamento;
import com.cursomc.domain.PagamentoComBoleto;
import com.cursomc.domain.PagamentoComCartao;
import com.cursomc.domain.Pedido;
import com.cursomc.domain.Produto;
import com.cursomc.domain.enums.EstadoPagamento;
import com.cursomc.domain.enums.PerfilCliente;
import com.cursomc.domain.enums.TipoCliente;
import com.cursomc.respositories.CategoriaRepository;
import com.cursomc.respositories.CidadeRepository;
import com.cursomc.respositories.ClienteRepository;
import com.cursomc.respositories.EnderecoRepository;
import com.cursomc.respositories.EstadoRepository;
import com.cursomc.respositories.ItemPedidoRepository;
import com.cursomc.respositories.PagamentoRepository;
import com.cursomc.respositories.PedidoRepository;
import com.cursomc.respositories.ProdutoRepository;

@Service
public class DBService {
	
	@Autowired //Instanciação automática
	private CategoriaRepository categoriaRepository;
	@Autowired
	private ProdutoRepository produtoRepository;
	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private CidadeRepository cidadeRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;
	@Autowired
	private ItemPedidoRepository itemPedidoRepository;
	@Autowired
	private BCryptPasswordEncoder pwdEncoder;
	
	
	public void instantiateTestDatabase() throws ParseException {
		//Instanciar as categorias
				Categoria cat1 = new Categoria(null, "Informática");
				Categoria cat2 = new Categoria(null, "Escritório");
				Categoria cat3 = new Categoria(null, "Cama, mesa e banho");
				Categoria cat4 = new Categoria(null, "Eletrônicos");
				Categoria cat5 = new Categoria(null, "Jardinagem");
				Categoria cat6 = new Categoria(null, "Decoração");
				Categoria cat7 = new Categoria(null, "Perfumaria");
				
				//Instanciar os Produtos
				Produto prod1 = new Produto(null, "Computador", 2000.00);
				Produto prod2 = new Produto(null, "Impressora", 800.00);
				Produto prod3 = new Produto(null, "Mouse", 80.00);
				Produto prod4 = new Produto(null, "Mesa de Escritório", 3000.00);
				Produto prod5 = new Produto(null, "Toalha", 50.00);
				Produto prod6 = new Produto(null, "Colcha", 200.00);
				Produto prod7 = new Produto(null, "TV true Color", 1200.00);
				Produto prod8 = new Produto(null, "Roçadeira", 800.00);
				Produto prod9 = new Produto(null, "Abajour", 100.00);
				Produto prod10 = new Produto(null, "Pendente", 180.00);
				Produto prod11 = new Produto(null, "Shampoo", 90.00);
				
				//Adicionar os Produtos para as referidas categorias
				cat1.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3));
				cat2.getProdutos().addAll(Arrays.asList(prod2, prod4));		
				cat3.getProdutos().addAll(Arrays.asList(prod5, prod6));		
				cat4.getProdutos().addAll(Arrays.asList(prod1, prod2, prod3, prod7));
				cat5.getProdutos().addAll(Arrays.asList(prod8));
				cat6.getProdutos().addAll(Arrays.asList(prod9, prod10));
				cat7.getProdutos().addAll(Arrays.asList(prod11));
				
				//Adicionar as Categorias para os referidos produtos
				prod1.getCategorias().addAll(Arrays.asList(cat1, cat4));
				prod2.getCategorias().addAll(Arrays.asList(cat1, cat2, cat4));
				prod3.getCategorias().addAll(Arrays.asList(cat1, cat4));
				prod4.getCategorias().addAll(Arrays.asList(cat2));
				prod5.getCategorias().addAll(Arrays.asList(cat3));
				prod6.getCategorias().addAll(Arrays.asList(cat3));
				prod7.getCategorias().addAll(Arrays.asList(cat4));
				prod8.getCategorias().addAll(Arrays.asList(cat5));
				prod9.getCategorias().addAll(Arrays.asList(cat6));
				prod10.getCategorias().addAll(Arrays.asList(cat6));
				prod11.getCategorias().addAll(Arrays.asList(cat7));
				
				//Instanciar os Estados
				Estado est1 = new Estado(null, "Minas Gerais");
				Estado est2 = new Estado(null, "São Paulo");
				
				//Instanciar as Cidades
				Cidade c1 = new Cidade(null, "Uberlândia", est1);
				Cidade c2 = new Cidade(null, "São Paulo", est2);
				Cidade c3 = new Cidade(null, "Campinas", est2);
				
				//Estados reconhecendo as próprias cidades
				est1.getCidades().addAll(Arrays.asList(c1));
				est2.getCidades().addAll(Arrays.asList(c2, c3));
				
				//Clientes
				Cliente cli1 = new Cliente(null, "Maria Silva", "virtualinsert+mariasilva@gmail.com", "36378912377", TipoCliente.PESSOAFISICA, pwdEncoder.encode("123"));
				Cliente cli2 = new Cliente(null, "Ana Costa", "virtualinsert+anacosta@gmail.com", "31628382740", TipoCliente.PESSOAFISICA, pwdEncoder.encode("123"));
				cli2.addPerfil(PerfilCliente.ADMIN);
				//Telefones
				cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
				cli1.getTelefones().addAll(Arrays.asList("27363323","93838393"));
				//Endereços
				Endereco e1 = new Endereco(null, "Rua Flores","300","Apto 303","Jardim","38220834", cli1, c1);
				Endereco e2 = new Endereco(null, "Avenida Matos","105","Sala 800","Centro","38777012", cli1, c2);
				Endereco e3 = new Endereco(null, "Avenida Floriano","2106",null,"Centro","38777012", cli2, c2);
				
				//Cliente reconhecendo os endereços
				cli1.getEnderecos().addAll(Arrays.asList(e1,e2));
				cli2.getEnderecos().addAll(Arrays.asList(e3));
				
				//Gerando os pedidos
				SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
				Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32:00"), cli1, e1);
				Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35:00"), cli1, e2);
				
				//Gerando os pagamentos
				Pagamento pagto1 = new PagamentoComCartao(null, EstadoPagamento.QUITADO, ped1, 6);
				ped1.setPagamento(pagto1);
				Pagamento pagto2 = new PagamentoComBoleto(null, EstadoPagamento.PENDENTE, ped2, sdf.parse("20/10/2017 00:00:00"), null);
				ped2.setPagamento(pagto2);
				
				cli1.getPedidos().addAll(Arrays.asList(ped1, ped2));
				
				//Gerando os itens dos pedidos
				ItemPedido ip1 = new ItemPedido(ped1, prod1, 0.00, 2000.00, 1);
				ItemPedido ip2 = new ItemPedido(ped1, prod3, 0.00, 80.00, 2);
				ItemPedido ip3 = new ItemPedido(ped2, prod2, 100.00, 800.00, 1);
				ped1.getItens().addAll(Arrays.asList(ip1,ip2));
				ped2.getItens().addAll(Arrays.asList(ip3));
				prod1.getItens().addAll(Arrays.asList(ip1));
				prod2.getItens().addAll(Arrays.asList(ip3));
				prod3.getItens().addAll(Arrays.asList(ip2));
				
				//Salvando todos os dados no banco de dados
				categoriaRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7));
				produtoRepository.saveAll(Arrays.asList(prod1, prod2, prod3, prod4, prod5, prod6, prod7, prod8, prod9, prod10, prod11));
				estadoRepository.saveAll(Arrays.asList(est1, est2));
				cidadeRepository.saveAll(Arrays.asList(c1, c2, c3));
				clienteRepository.saveAll(Arrays.asList(cli1, cli2));
				enderecoRepository.saveAll(Arrays.asList(e1,e2,e3));
				pedidoRepository.saveAll(Arrays.asList(ped1,ped2));
				pagamentoRepository.saveAll(Arrays.asList(pagto1,pagto2));
				itemPedidoRepository.saveAll(Arrays.asList(ip1,ip2,ip3));
	}

}
