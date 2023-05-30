/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo.dao;

import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import modelo.dao.exceptions.NonexistentEntityException;
import modelo.entidades.Pedidos;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import java.io.IOException;
import modelo.entidades.Clientes;
import modelo.entidades.Productos_Pedidos;
import org.apache.pdfbox.pdmodel.graphics.image.PDImageXObject;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author alanr
 */
public class PedidosJpaController implements Serializable {

    public PedidosJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Pedidos pedidos) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(pedidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Pedidos pedidos) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            pedidos = em.merge(pedidos);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Long id = pedidos.getId();
                if (findPedidos(id) == null) {
                    throw new NonexistentEntityException("The pedidos with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Long id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Pedidos pedidos;
            try {
                pedidos = em.getReference(Pedidos.class, id);
                pedidos.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The pedidos with id " + id + " no longer exists.", enfe);
            }
            em.remove(pedidos);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Pedidos> findPedidosEntities() {
        return findPedidosEntities(true, -1, -1);
    }

    public List<Pedidos> findPedidosEntities(int maxResults, int firstResult) {
        return findPedidosEntities(false, maxResults, firstResult);
    }

    private List<Pedidos> findPedidosEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Pedidos.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Pedidos findPedidos(Long id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Pedidos.class, id);
        } finally {
            em.close();
        }
    }

    public int getPedidosCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Pedidos> rt = cq.from(Pedidos.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

    public List<Pedidos> findPedidosByIDCliente(long idCliente) {
        EntityManager em = getEntityManager();
        try {
            // Crear la consulta para obtener los equipos de la liga
            TypedQuery<Pedidos> query = em.createQuery("SELECT p FROM Pedidos p where p.cliente.id = :idCliente", Pedidos.class);
            query.setParameter("idCliente", idCliente);

            // Ejecutar la consulta y devolver los resultados
            return query.getResultList();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public String generarFactura(long pedidoId, List<Productos_Pedidos> pp, Clientes cliente, String ruta) {
        EntityManager em = emf.createEntityManager();
        String nombreArchivo = "";
        try {
            em.getTransaction().begin();

            // Obtener el pedido de la base de datos
            Pedidos pedido = em.find(Pedidos.class, pedidoId);

            if (pedido != null) {
                // Crear un nuevo documento PDF
                PDDocument document = new PDDocument();
                PDPage page = new PDPage(PDRectangle.A4);
                document.addPage(page);
                double acumulador = 0;
                double iva = 0.21;
                DecimalFormat decimalFormat = new DecimalFormat("#.00");

                // Crear un objeto PDPageContentStream para escribir en la página
                PDPageContentStream contentStream = new PDPageContentStream(document, page);
                PDImageXObject image = PDImageXObject.createFromFile(ruta + "\\..\\img\\background.jpg", document);

                // Configurar la fuente y el tamaño de la fuente
                contentStream.setFont(PDType1Font.HELVETICA_BOLD, 12);

                // Escribir el contenido de la factura utilizando los datos del pedido
                contentStream.drawImage(image, 0, 0, 595, 842);
                contentStream.beginText();
                contentStream.newLineAtOffset(420, 650);
                contentStream.showText(cliente.getNombre() + " " + cliente.getApellidos());
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText(cliente.getEmail());
                contentStream.newLineAtOffset(0, -15);
                contentStream.showText(cliente.getTelefono());
                contentStream.newLineAtOffset(-350, -90);
                for (int i = 0; i < pp.size(); i++) {
                    contentStream.newLineAtOffset(0, -35);
                    contentStream.showText(pp.get(i).getProducto().getNombreProducto());
                    contentStream.newLineAtOffset(300, 0);
                    contentStream.showText(pp.get(i).getCantidad() + "");
                    contentStream.newLineAtOffset(60, 0);
                    contentStream.showText(pp.get(i).getProducto().getPrecio() + "\u20AC");
                    contentStream.newLineAtOffset(70, 0);
                    contentStream.showText(pp.get(i).getCantidad() * pp.get(i).getProducto().getPrecio() + "\u20AC");
                    acumulador += pp.get(i).getCantidad() * pp.get(i).getProducto().getPrecio();
                    contentStream.newLineAtOffset(-430, 0);
                }

                String baseImponible = decimalFormat.format(acumulador);
                String totalBase = decimalFormat.format(acumulador * 0.21);
                String totalAproximado = decimalFormat.format(acumulador + (acumulador * 0.21));

                contentStream.endText();
                contentStream.beginText();
                contentStream.newLineAtOffset(65, 218);
                contentStream.showText(iva * 100 + "%"); //iva
                contentStream.newLineAtOffset(90, 0);
                contentStream.showText(baseImponible + "\u20AC"); // baseImponible 
                contentStream.newLineAtOffset(150, 0);
                contentStream.showText(iva + ""); //cuotaIva
                contentStream.newLineAtOffset(100, 0);
                contentStream.showText(totalBase + "\u20AC"); // total base
                contentStream.newLineAtOffset(70, 0);
                contentStream.showText(totalAproximado + "\u20AC"); // total aproximado
                contentStream.endText();
                contentStream.close();

                LocalDateTime currentDateTime = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
                String formattedDate = currentDateTime.format(formatter);

                // Guardar el documento PDF
                document.save(ruta + "\\" + "factura" + pedidoId + "-" + formattedDate + ".pdf");
                nombreArchivo = "factura" + pedidoId + "-" + formattedDate + ".pdf";
                document.close();

                System.out.println("Factura generada exitosamente.");
            } else {
                System.out.println("Pedido no encontrado.");
            }

            em.getTransaction().commit();

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            em.close();
        }
        return nombreArchivo;
    }
}
