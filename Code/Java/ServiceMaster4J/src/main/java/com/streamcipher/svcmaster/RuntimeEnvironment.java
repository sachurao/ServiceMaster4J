package com.streamcipher.svcmaster;

import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;

public class RuntimeEnvironment {
	private static final FileSystem fileSystem = FileSystems.getDefault();
	public static final String ROOT_DIR_LOCATION = System.getenv("SM4J_HOME");
	public static final Path ROOT_DIR_PATH = fileSystem.getPath(ROOT_DIR_LOCATION); 
	public static final Path CONFIG_DIR_PATH = fileSystem.getPath(ROOT_DIR_LOCATION,"config");
	public static final Path LOG_DIR_PATH = fileSystem.getPath(ROOT_DIR_LOCATION,"logs");
}
