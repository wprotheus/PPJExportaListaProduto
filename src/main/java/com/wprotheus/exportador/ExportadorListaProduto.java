package com.wprotheus.exportador;

import com.wprotheus.model.Produto;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

public interface ExportadorListaProduto {
    static ExportadorListaProduto newInstace() {
        return newInstace("html");
    }

    static ExportadorListaProduto newInstace(String extensao) {
        String ext = extensao.toLowerCase();
        ext = Character.toUpperCase(ext.charAt(0)) + ext.substring(1) ;
        var className = ExportadorListaProduto.class.getName() + ext;
        System.out.println(className);

        try {
            var clazz = Class.forName(className);
            var constructor = clazz.getConstructor();
            Object obj = constructor.newInstance();
            if(obj instanceof ExportadorListaProduto exportador)
                return exportador;
            throw new RuntimeException("A classe localizada para exportar produtos nao implementa " + ExportadorListaProduto.class.getSimpleName());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("msg personalizada", e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    public String abrirTabela();

    public String fecharTabela();

    public String abrirLinha();

    public String fecharLinha();

    public String abrirLinhaTitulos();

    public String fecharLinhaTitulos();

    public String criarColuna(Object valor);

    public String exportar(List<Produto> produto);
}