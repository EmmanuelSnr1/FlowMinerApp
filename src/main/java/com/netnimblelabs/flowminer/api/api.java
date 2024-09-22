/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.netnimblelabs.flowminer.api;

import com.netnimblelabs.flowminer.models.ProcessFile;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import com.netnimblelabs.flowminer.util.SessionUtil;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.util.List;
import javax.ws.rs.Path;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.deckfour.xes.in.XesXmlParser;
import org.deckfour.xes.model.XLog;
import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

/**
 *
 * @author admin
 */
@Path("/resource")
public class api {

   
}
